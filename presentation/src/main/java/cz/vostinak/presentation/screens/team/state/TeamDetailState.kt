package cz.vostinak.presentation.screens.team.state

/**
 * TeamState detail screen state.
 */
sealed interface TeamDetailState {
    /** Success state. */
    data class Success(val team: TeamState): TeamDetailState
    /** Loading state. */
    data object Loading: TeamDetailState
    /** Error state. */
    data class Error(val error: Throwable): TeamDetailState
}
