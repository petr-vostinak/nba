package cz.vostinak.presentation.screens.team

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.vostinak.domain.usecases.GetTeamUseCase
import cz.vostinak.presentation.mapper.toState
import cz.vostinak.presentation.screens.team.state.TeamDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * TeamState detail view model.
 */
@HiltViewModel
class TeamDetailViewModel @Inject constructor(
    private val getTeamUseCase: GetTeamUseCase
): ViewModel() {

    private val _teamDetailState = MutableStateFlow<TeamDetailState>(TeamDetailState.Loading)
    val teamDetailState = _teamDetailState.asStateFlow()

    /**
     * Get team detail.
     * @param teamId TeamState ID.
     */
    fun getTeamDetail(teamId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            _teamDetailState.value = TeamDetailState.Loading

            try {
                val data = getTeamUseCase(teamId)
                _teamDetailState.value = TeamDetailState.Success(data.toState())
            } catch (e: Exception) {
                _teamDetailState.value = TeamDetailState.Error(e)
            }
        }
    }
}