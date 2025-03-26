package cz.vostinak.presentation.mapper

import cz.vostinak.domain.core.entities.Player
import cz.vostinak.domain.favorite.entities.FavoritePlayerItem
import cz.vostinak.presentation.screens.player.state.PlayerState

/**
 * Convert Player to PlayerState.
 * @return PlayerState.
 */
fun Player.toState() = PlayerState(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    position = this.position,
    team = this.team.toState(),
    height = this.height,
    weight = this.weight,
    college = this.college,
    country = this.country,
    draftYear = this.draftYear,
    draftRound = this.draftRound,
    draftNumber = this.draftNumber,
    jerseyNumber = this.jerseyNumber
)

fun PlayerState.toDomainFavoriteItem() = FavoritePlayerItem(
    id = id,
    fullName = "$firstName $lastName",
    teamFullName = team.fullName,
    imageUrl = "https://randomuser.me/api/portraits/men/${jerseyNumber}.jpg"
)