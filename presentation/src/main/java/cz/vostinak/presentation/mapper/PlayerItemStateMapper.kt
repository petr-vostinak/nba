package cz.vostinak.presentation.mapper

import cz.vostinak.domain.entities.PlayerItem
import cz.vostinak.presentation.screens.list.state.PlayerItemState

/**
 * Convert Player to PlayerItemState.
 * @return PlayerItemState.
 */
fun PlayerItem.toState() = PlayerItemState(
    id = this.id,
    fullName = this.fullName,
    teamFullName = this.teamFullName,
    jerseyNumber = this.jerseyNumber
)