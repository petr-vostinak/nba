package cz.vostinak.presentation.screens.team.state

/**
 * TeamState detail screen state.
 */
data class TeamDetailState(
    /** Is loading */
    val isLoading: Boolean = true,
    /** TeamState data */
    val team: TeamState? = null,
    /** Error */
    val error: Throwable? = null
)
