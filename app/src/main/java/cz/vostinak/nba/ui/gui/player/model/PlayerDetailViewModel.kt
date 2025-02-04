package cz.vostinak.nba.ui.gui.player.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Player detail view model.
 */
@HiltViewModel
class PlayerDetailViewModel @Inject constructor(
    private val repository: PlayerDetailRepository
) : ViewModel() {

    /** Player detail state. */
    val playerDetailState = repository.playerDetailState.asStateFlow()

    /**
     * Get player detail.
     * @param playerId Player ID.
     */
    fun getPlayerDetail(playerId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPlayerDetail(playerId)
        }
    }
}