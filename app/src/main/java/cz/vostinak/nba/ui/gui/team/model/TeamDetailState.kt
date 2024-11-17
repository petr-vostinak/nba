package cz.vostinak.nba.ui.gui.team.model

/**
 * Team detail screen state.
 */
data class TeamDetailState(
    /** Is loading */
    val isLoading: Boolean = true,
    /** Team data */
    val team: Team? = null,
    /** Error */
    val error: Throwable? = null
)
