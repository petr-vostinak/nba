package cz.vostinak.data.api.response

import cz.vostinak.data.api.to.TeamTO

/**
 * Team detail response.
 */
data class TeamDetailResponse(
    /** Team data */
    val data: TeamTO
)
