package cz.vostinak.presentation.screens.list.state

/**
 * Players list state.
 */
sealed interface PlayersListState {
    /** Success state. */
    data class Success(
        val players: List<PlayerItemState>,
        val isLoadingMore: Boolean = false
    ) : PlayersListState
    /** Loading state. */
    data object Loading : PlayersListState
    /** Error state. */
    data class Error(val error: Throwable) : PlayersListState
}
