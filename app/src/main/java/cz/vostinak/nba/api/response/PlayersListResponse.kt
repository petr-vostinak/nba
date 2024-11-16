package cz.vostinak.nba.api.response

import cz.vostinak.nba.ui.gui.list.model.Player

/**
 * Players list response.
 */
data class PlayersListResponse(
    /** List of players. */
    val data: List<Player>,
    /** Paging metadata. */
    val meta: Meta
)
