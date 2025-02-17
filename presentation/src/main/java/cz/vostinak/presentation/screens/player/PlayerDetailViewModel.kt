package cz.vostinak.presentation.screens.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.vostinak.domain.usecases.GetPlayerUseCase
import cz.vostinak.presentation.mapper.toState
import cz.vostinak.presentation.screens.player.state.PlayerDetailState
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
    private val getPlayerUseCase: GetPlayerUseCase
) : ViewModel() {

    private val _playerDetailState = MutableStateFlow<PlayerDetailState>(PlayerDetailState.Loading)
    val playerDetailState = _playerDetailState.asStateFlow()

    /**
     * Get player detail.
     * @param playerId PlayerItemState ID.
     */
    fun getPlayerDetail(playerId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            _playerDetailState.value = PlayerDetailState.Loading

            try {
                val data = getPlayerUseCase(playerId)
                _playerDetailState.value = PlayerDetailState.Success(data.toState())
            } catch (e: Exception) {
                _playerDetailState.value = PlayerDetailState.Error(e)
            }
        }
    }
}