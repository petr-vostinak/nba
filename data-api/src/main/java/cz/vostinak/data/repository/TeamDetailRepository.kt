package cz.vostinak.data.repository

import cz.vostinak.data.api.NbaTeamsRestAPI
import cz.vostinak.data.api.to.TeamTO
import javax.inject.Inject

/**
 * TeamState detail repository.
 */
class TeamDetailRepository @Inject constructor(
    private val nbaTeamRestApi: NbaTeamsRestAPI
) {
    /**
     * Get team detail from API.
     * @param teamId TeamState ID.
     */
    suspend fun getTeamDetail(teamId: Long): TeamTO {
        try {
            return nbaTeamRestApi.getTeam(teamId).data
        } catch (e: Exception) {
            throw e
        }

    }
}