package cz.vostinak.room.player

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Player database entity.
 */
@Entity(tableName = "players")
data class PlayerEntity(
    /** Player ID */
    @PrimaryKey
    val id: Long,
    /** First name */
    val firstName: String,
    /** Last name */
    val lastName: String,
    /** Position */
    val position: String,
    /** Height */
    val height: String,
    /** Weight */
    val weight: String,
    /** Jersey number */
    val jerseyNumber: String,
    /** College */
    val college: String,
    /** Country */
    val country: String,
    /** Draft year */
    val draftYear: String,
    /** Draft round */
    val draftRound: String,
    /** Draft number */
    val draftNumber: String,
    /** Team info */
    val teamId: Long,
    /** Team name */
    val teamName: String,
    /** Timestamp of last update */
    val timestamp: Long
)
