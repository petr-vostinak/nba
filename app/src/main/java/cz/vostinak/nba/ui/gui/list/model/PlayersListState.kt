package cz.vostinak.nba.ui.gui.list.model

/**
 * Players list state.
 */
data class PlayersListState(
    /** List of players */
    val players: List<Player> = emptyList(),
    /** Is loading */
    val isLoading: Boolean = true,
    /** Error */
    val error: Throwable? = null
)
