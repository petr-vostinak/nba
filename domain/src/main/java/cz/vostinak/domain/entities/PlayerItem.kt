package cz.vostinak.domain.entities

/**
 * Player item.
 */
data class PlayerItem(
    /** Id */
    val id: Long,
    /** Player full name */
    val fullName: String,
    /** TeamState full name */
    val teamFullName: String,
    /** Jersey number */
    val jerseyNumber: String
)
