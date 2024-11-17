package cz.vostinak.nba.ui.gui.team.model

import cz.vostinak.nba.api.NbaTeamsRestAPI
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

/**
 * Team detail repository.
 */
class TeamDetailRepository @Inject constructor(
    private val nbaTeamRestApi: NbaTeamsRestAPI
) {
    /** Team detail state. */
    val teamDetailState = MutableStateFlow(TeamDetailState())

    /**
     * Get team detail from API.
     * @param teamId Team ID.
     */
    suspend fun getTeamDetail(teamId: Long) {
        val response = nbaTeamRestApi.getTeam(teamId)

        teamDetailState.value = TeamDetailState(
            isLoading = false,
            team = response.data
        )
    }
}