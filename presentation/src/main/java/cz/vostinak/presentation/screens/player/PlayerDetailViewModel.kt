package cz.vostinak.presentation.screens.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.vostinak.domain.usecases.GetPlayerUseCase
import cz.vostinak.presentation.screens.player.state.PlayerDetailState
import cz.vostinak.presentation.mapper.toState
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

    private val _playerDetailState = MutableStateFlow(PlayerDetailState())
    val playerDetailState = _playerDetailState.asStateFlow()

    /**
     * Get player detail.
     * @param playerId PlayerItemState ID.
     */
    fun getPlayerDetail(playerId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            _playerDetailState.value = PlayerDetailState()

            try {
                val response = getPlayerUseCase(playerId)

                _playerDetailState.value = _playerDetailState.value.copy(
                    isLoading = false,
                    player = response.toState()
                )
            } catch (e: Exception) {
                _playerDetailState.value = _playerDetailState.value.copy(
                    isLoading = false,
                    error = e
                )
            }
        }
    }
}