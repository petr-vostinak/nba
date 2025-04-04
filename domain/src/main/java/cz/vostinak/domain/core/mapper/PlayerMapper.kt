package cz.vostinak.domain.core.mapper

import cz.vostinak.data.api.to.PlayerTO
import cz.vostinak.domain.core.entities.DataOrigin
import cz.vostinak.domain.core.entities.Player
import cz.vostinak.domain.core.entities.Team
import cz.vostinak.room.player.PlayerEntity

/**
 * Convert PlayerTO to Player.
 * @return Player.
 */
fun PlayerTO.toDomain() = Player(
    id = this.id,
    firstName = this.firstName ?: "",
    lastName = this.lastName ?: "",
    position = this.position ?: "---",
    team = Team(
        id = this.team?.id ?: 0,
        abbreviation = this.team?.abbreviation ?: "---",
        city = this.team?.city ?: "---",
        conference = this.team?.conference ?: "---",
        division = this.team?.division ?: "---",
        fullName = this.team?.fullName ?: "---",
        name = this.team?.name ?: "---",
        logoResourceIdRes = null,
        origin = null
    ),
    height = this.height ?: "---",
    weight = this.weight ?: "---",
    college = this.college ?: "---",
    draftYear = this.draftYear?.toString() ?: "---",
    draftRound = this.draftRound?.toString() ?: "---",
    draftNumber = this.draftNumber?.toString() ?: "---",
    country = this.country ?: "---",
    jerseyNumber = this.jerseyNumber ?: "NA",
    origin = DataOrigin.API
)

/**
 * Convert PlayerEntity to Player.
 * @param origin Data origin
 * @return Player.
 */
fun PlayerEntity.toDomain(origin: DataOrigin) = Player(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    position = this.position,
    team = Team(
        id = this.teamId,
        abbreviation = "---",
        city = "---",
        conference = "---",
        division = "---",
        fullName = this.teamName,
        name = "---",
        logoResourceIdRes = null,
        origin = null
    ),
    height = this.height,
    weight = this.weight,
    college = this.college,
    draftYear = this.draftYear,
    draftRound = this.draftRound,
    draftNumber = this.draftNumber,
    country = this.country,
    jerseyNumber = this.jerseyNumber,
    origin = origin
)

/**
 * Convert PlayerTO to PlayerEntity.
 * @return PlayerEntity.
 */
fun PlayerTO.toEntity() = PlayerEntity(
    id = this.id,
    firstName = this.firstName ?: "",
    lastName = this.lastName ?: "",
    position = this.position ?: "---",
    teamId = this.team?.id ?: 0,
    teamName = this.team?.fullName ?: "---",
    height = this.height ?: "---",
    weight = this.weight ?: "---",
    college = this.college ?: "---",
    draftYear = this.draftYear?.toString() ?: "---",
    draftRound = this.draftRound?.toString() ?: "---",
    draftNumber = this.draftNumber?.toString() ?: "---",
    country = this.country ?: "---",
    jerseyNumber = this.jerseyNumber ?: "NA",
    timestamp = System.currentTimeMillis()
)