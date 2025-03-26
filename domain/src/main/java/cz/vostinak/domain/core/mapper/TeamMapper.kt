package cz.vostinak.domain.core.mapper

import cz.vostinak.data.api.to.TeamTO
import cz.vostinak.domain.core.entities.DataOrigin
import cz.vostinak.domain.core.entities.Team
import cz.vostinak.room.team.TeamEntity

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
    logoResourceIdRes = null,
    origin = DataOrigin.API
)

/**
 * Convert TeamEntity to Team.
 * @param origin Data origin.
 * @return Team.
 */
fun TeamEntity.toDomain(origin: DataOrigin) = Team(
    id = this.id,
    name = this.name ?: "",
    fullName = this.fullName ?: "",
    city = this.city ?: "---",
    abbreviation = this.abbreviation ?: "---",
    conference = this.conference ?: "---",
    division = this.division ?: "---",
    logoResourceIdRes = null,
    origin = origin
)

/**
 * Convert TeamTO to TeamEntity.
 * @return TeamEntity.
 */
fun TeamTO.toEntity() = TeamEntity(
    id = this.id,
    name = this.name ?: "",
    fullName = this.fullName ?: "",
    city = this.city ?: "---",
    abbreviation = this.abbreviation ?: "---",
    conference = this.conference ?: "---",
    division = this.division ?: "---",
    timestamp = System.currentTimeMillis()
)