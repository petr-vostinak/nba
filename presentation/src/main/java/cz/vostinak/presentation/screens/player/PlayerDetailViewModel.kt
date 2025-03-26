package cz.vostinak.presentation.screens.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.vostinak.domain.favorite.usecase.AddFavoritePlayerUseCase
import cz.vostinak.domain.favorite.usecase.IsFavoritePlayerUseCase
import cz.vostinak.domain.favorite.usecase.RemoveFavoritePlayerUseCase
import cz.vostinak.domain.core.usecases.GetPlayerUseCase
import cz.vostinak.presentation.mapper.toDomainFavoriteItem
import cz.vostinak.presentation.mapper.toState
import cz.vostinak.presentation.screens.player.state.PlayerDetailScreenState
import cz.vostinak.presentation.screens.player.state.PlayerState
import cz.vostinak.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * PlayerItemState detail view model.
 */
@HiltViewModel
class PlayerDetailViewModel @Inject constructor(
    private val getPlayerUseCase: GetPlayerUseCase,
    private val addFavoritePlayerUseCase: AddFavoritePlayerUseCase,
    private val removeFavoritePlayerUseCase: RemoveFavoritePlayerUseCase,
    private val isFavoritePlayerUseCase: IsFavoritePlayerUseCase
) : ViewModel() {

    private val _playerDetailState = MutableStateFlow<UiState<PlayerDetailScreenState>>(UiState.Loading)
    val playerDetailState = _playerDetailState.asStateFlow()

    private var cachedPlayer: PlayerState? = null

    /**
     * Get player detail.
     * @param playerId PlayerItemState ID.
     */
    fun getPlayerDetail(playerId: Long) {
        if(cachedPlayer?.id == playerId) {
            _playerDetailState.value = UiState.Success(PlayerDetailScreenState(cachedPlayer!!))
            isFavoritePlayer(playerId)
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            _playerDetailState.value = UiState.Loading

            try {
                getPlayerUseCase(playerId).collect { player ->
                    cachedPlayer = player.toState()
                    _playerDetailState.value = UiState.Success(PlayerDetailScreenState(cachedPlayer!!))
                    isFavoritePlayer(playerId)
                }
            } catch (e: Exception) {
                _playerDetailState.value = UiState.Error(e)
            }
        }
    }

    /**
     * Add player to favorites.
     * @param player PlayerItemState.
     */
    fun addFavoritePlayer(player: PlayerState) {
        viewModelScope.launch {
            addFavoritePlayerUseCase(player.toDomainFavoriteItem())
            getPlayerDetail(player.id)
        }
    }

    /**
     * Remove player from favorites.
     * @param playerId PlayerItemState ID.
     */
    fun removeFavoritePlayer(playerId: Long) {
        viewModelScope.launch {
            removeFavoritePlayerUseCase(playerId)
            getPlayerDetail(playerId.toLong())
        }
    }

    /**
     * Check if player is favorite.
     * @param playerId PlayerItemState ID.
     */
    private fun isFavoritePlayer(playerId: Long) {
        viewModelScope.launch {
            _playerDetailState.value = when(isFavoritePlayerUseCase(playerId)) {
                true -> UiState.Success(PlayerDetailScreenState(cachedPlayer!!, true))
                false -> UiState.Success(PlayerDetailScreenState(cachedPlayer!!, false))
            }
        }
    }
}