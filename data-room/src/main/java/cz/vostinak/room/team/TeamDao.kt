package cz.vostinak.room.team

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Team DAO.
 */
@Dao
interface TeamDao {
    /**
     * Get team by ID.
     * Where timestamp is greater than given value.
     * Default value is 24 hours ago.
     * @param teamId Player ID
     * @param timestamp Timestamp
     * @return Team entity
     */
    @Query("SELECT * FROM teams WHERE id = :teamId AND timestamp > :timestamp")
    fun getTeam(teamId: Long, timestamp: Long = System.currentTimeMillis() - 24 * 60 * 60 * 1000): TeamEntity?

    /**
     * Insert team.
     * @param team Team entity
     */
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    fun insertTeam(team: TeamEntity)
}