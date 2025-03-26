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
     * @return Player entity
     */
    @Query("SELECT * FROM players WHERE id = :playerId")
    fun getPlayer(playerId: Long): PlayerEntity?

    /**
     * Insert player.
     * @param player Player entity
     */
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    fun insertPlayer(player: PlayerEntity)

    /**
     * Delete old data of players older than 24 hours.
     */
    @Query("DELETE FROM players WHERE timestamp < :timestamp")
    fun deleteOldData(timestamp: Long = System.currentTimeMillis() - 24 * 60 * 60 * 1000)
}