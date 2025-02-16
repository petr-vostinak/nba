package cz.vostinak.presentation.screens.list.state

/**
 * Players list state.
 */
data class PlayersListState(
    /** List of players */
    val players: List<PlayerItemState> = emptyList(),
    /** Is loading */
    val isLoading: Boolean = true,
    /** Error */
    val error: Throwable? = null
)
