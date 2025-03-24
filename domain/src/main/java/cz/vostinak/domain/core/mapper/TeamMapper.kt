package cz.vostinak.domain.core.mapper

import cz.vostinak.data.api.to.TeamTO
import cz.vostinak.domain.core.entities.Team

/**
 * Convert TeamTO to Team.
 * @return Team.
 */
fun TeamTO.toDomain() = Team(
    id = this.id,
    name = this.name ?: "",
    fullName = this.fullName ?: "",
    city = this.city ?: "---",
    abbreviation = this.abbreviation ?: "---",
    conference = this.conference ?: "---",
    division = this.division ?: "---",
    logoResourceIdRes = null
)