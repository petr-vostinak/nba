package cz.vostinak.presentation.screens.list.state

/**
 * PlayerItemState.
 */
data class PlayerItemState(
    /** Id */
    val id: Long,
    /** Player full name */
    val fullName: String,
    /** TeamState full name */
    val teamFullName: String,
    /** Jersey number */
    val jerseyNumber: String
)
