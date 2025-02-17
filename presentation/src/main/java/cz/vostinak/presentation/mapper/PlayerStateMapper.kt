package cz.vostinak.presentation.mapper

import cz.vostinak.domain.entities.Player
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