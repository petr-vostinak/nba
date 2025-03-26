package cz.vostinak.room.team

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Team database entity.
 */
@Entity(tableName = "teams")
data class TeamEntity(
    /** Team ID */
    @PrimaryKey
    val id: Long,
    /** Team short name */
    val name: String?,
    /** Team full name */
    val fullName: String?,
    /** Team city */
    val city: String?,
    /** Team abbreviation */
    val abbreviation: String?,
    /** Team conference */
    val conference: String?,
    /** Team division */
    val division: String?,
    /** Timestamp of last update */
    val timestamp: Long
)
