package cz.vostinak.nba.ui.gui.team.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Team detail view model.
 */
@HiltViewModel
class TeamDetailViewModel @Inject constructor(
    private val repository: TeamDetailRepository
): ViewModel() {

    /** Team detail state */
    val teamDetailState = repository.teamDetailState.asStateFlow()

    /**
     * Get team detail.
     * @param teamId Team ID.
     */
    fun getTeamDetail(teamId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getTeamDetail(teamId)
        }
    }
}