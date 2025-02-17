package cz.vostinak.presentation.screens.player.state

/**
 * Player detail state.
 */
sealed interface PlayerDetailState {
    /** Success state. */
    data class Success(val player: PlayerState): PlayerDetailState
    /** Loading state. */
    data object Loading: PlayerDetailState
    /** Error state. */
    data class Error(val error: Throwable): PlayerDetailState
}
