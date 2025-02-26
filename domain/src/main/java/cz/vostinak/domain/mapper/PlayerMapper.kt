package cz.vostinak.domain.mapper

import cz.vostinak.data.api.to.PlayerTO
import cz.vostinak.domain.entities.Player
import cz.vostinak.domain.entities.Team
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
        logoResourceIdRes = null
    ),
    height = this.height ?: "---",
    weight = this.weight ?: "---",
    college = this.college ?: "---",
    draftYear = this.draftYear?.toString() ?: "---",
    draftRound = this.draftRound?.toString() ?: "---",
    draftNumber = this.draftNumber?.toString() ?: "---",
    country = this.country ?: "---",
    jerseyNumber = this.jerseyNumber ?: "NA"
)

/**
 * Convert PlayerEntity to Player.
 * @return Player.
 */
fun PlayerEntity.toDomain() = Player(
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
        logoResourceIdRes = null
    ),
    height = this.height,
    weight = this.weight,
    college = this.college,
    draftYear = this.draftYear,
    draftRound = this.draftRound,
    draftNumber = this.draftNumber,
    country = this.country,
    jerseyNumber = this.jerseyNumber
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