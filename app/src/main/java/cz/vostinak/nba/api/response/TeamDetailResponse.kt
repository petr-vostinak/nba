package cz.vostinak.nba.api.response

import cz.vostinak.nba.ui.gui.team.model.Team

/**
 * Team detail response.
 */
data class TeamDetailResponse(
    /** Team data */
    val data: Team
)
