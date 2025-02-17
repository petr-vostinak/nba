package cz.vostinak.domain.entities

/**
 * Player entity.
 */
data class Player(
    /** Id */
    val id: Long,
    /** First name */
    val firstName: String,
    /** Last name */
    val lastName: String,
    /** TeamState */
    val team: Team,
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
