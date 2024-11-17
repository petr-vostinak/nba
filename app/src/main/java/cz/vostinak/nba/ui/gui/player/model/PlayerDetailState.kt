package cz.vostinak.nba.ui.gui.player.model

import cz.vostinak.nba.ui.gui.list.model.Player

/**
 * Player detail screen state.
 */
data class PlayerDetailState(
    /** Is loading */
    val isLoading: Boolean = true,
    /** Player data */
    val player: Player? = null,
    /** Error */
    val error: Throwable? = null
)
