package cz.vostinak.presentation.screens.player.state

/**
 * Player detail screen state.
 */
data class PlayerDetailScreenState(
    /** Player data */
    val playerState: PlayerState,
    /** Is player favorite */
    val isFavorite: Boolean? = null
)
