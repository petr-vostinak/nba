package cz.vostinak.presentation.screens.player.state

import cz.vostinak.presentation.screens.team.state.TeamState

/**
 * PlayerItemState.
 */
data class PlayerState(
    /** Id */
    val id: Long,
    /** First name */
    val firstName: String,
    /** Last name */
    val lastName: String,
    /** TeamState */
    val team: TeamState,
    /** Position */
    val position: String,
    /** Height */
    val height: String,
    /** Weight */
    val weight: String,
    /** College */
    val college: String,
    /** Country */
    val country: String?,
    /** Draft year */
    val draftYear: String,
    /** Draft round */
    val draftRound: String,
    /** Draft number */
    val draftNumber: String,
    /** Jersey number */
    val jerseyNumber: String
)
