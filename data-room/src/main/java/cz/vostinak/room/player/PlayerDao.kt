package cz.vostinak.room.player

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Player DAO.
 */
@Dao
interface PlayerDao {
    /**
     * Get player by ID.
     * Where timestamp is greater than given value.
     * Default value is 24 hours ago.
     * @param playerId Player ID
     * @param timestamp Timestamp
     * @return Player entity
     */
    @Query("SELECT * FROM players WHERE id = :playerId AND timestamp > :timestamp")
    fun getPlayer(playerId: Long, timestamp: Long = System.currentTimeMillis() - 24 * 60 * 60 * 1000): PlayerEntity?

    /**
     * Insert player.
     * @param player Player entity
     */
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    fun insertPlayer(player: PlayerEntity)
}