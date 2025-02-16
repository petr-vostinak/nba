package cz.vostinak.presentation.screens.player.state

/**
 * PlayerItemState detail screen state.
 */
data class PlayerDetailState(
    /** Is loading */
    val isLoading: Boolean = true,
    /** PlayerItemState data */
    val player: PlayerState? = null,
    /** Error */
    val error: Throwable? = null
)
