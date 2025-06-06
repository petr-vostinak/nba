package cz.vostinak.presentation.mapper

import cz.vostinak.domain.core.entities.DataOrigin
import cz.vostinak.domain.core.entities.Team
import cz.vostinak.presentation.screens.team.state.TeamState

/**
 * Convert Team to TeamState.
 * @return TeamState.
 */
fun Team.toState() = TeamState(
    id = id,
    name = name,
    fullName = fullName,
    city = city,
    abbreviation = abbreviation,
    conference = conference,
    division = division,
    logoResourceIdRes = logoResourceIdRes,
    origin = when(this.origin) {
        DataOrigin.API -> StateDataOrigin.API
        DataOrigin.DB_CURRENT -> StateDataOrigin.DB_CURRENT
        DataOrigin.DB_EXPIRED -> StateDataOrigin.DB_EXPIRED
        else -> null
    }
)