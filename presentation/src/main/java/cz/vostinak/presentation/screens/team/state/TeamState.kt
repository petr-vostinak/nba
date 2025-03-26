package cz.vostinak.presentation.screens.team.state

/**
 * TeamState data class.
 */
data class TeamState(
    /** TeamState ID */
    val id: Long,
    /** TeamState short name */
    val name: String,
    /** TeamState full name */
    val fullName: String,
    /** TeamState city */
    val city: String,
    /** TeamState abbreviation */
    val abbreviation: String,
    /** TeamState conference */
    val conference: String,
    /** TeamState division */
    val division: String,
    /** TeamState logo resource ID */
    val logoResourceIdRes: Int?
)
