package cz.vostinak.domain.mapper

import cz.vostinak.data.api.to.PlayerTO
import cz.vostinak.domain.entities.Player
import cz.vostinak.domain.entities.Team

/**
 * Convert PlayerTO to Player.
 * @return Player.
 */
fun PlayerTO.toDomain() = Player(
    id = this.id,
    firstName = this.firstName ?: "",
    lastName = this.lastName ?: "",
    position = this.position ?: "",
    team = Team(
        id = this.team?.id ?: 0,
        abbreviation = this.team?.abbreviation ?: "",
        city = this.team?.city ?: "",
        conference = this.team?.conference ?: "",
        division = this.team?.division ?: "",
        fullName = this.team?.fullName ?: "",
        name = this.team?.name ?: ""
    ),
    height = this.height ?: "",
    weight = this.weight ?: "",
    college = this.college ?: "",
    draftYear = this.draftYear.toString(),
    draftRound = this.draftRound.toString(),
    draftNumber = this.draftNumber.toString(),
    country = this.country ?: "",
    jerseyNumber = this.jerseyNumber.toString()
)