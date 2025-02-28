package cz.vostinak.room.repository

import cz.vostinak.room.player.PlayerDao
import cz.vostinak.room.player.PlayerEntity
import javax.inject.Inject

/**
 * Player database repository.
 */
class PlayerDbRepository @Inject constructor(
    private val playerDao: PlayerDao
) {

    /**
     * Get player by ID if there is a database record not older than 24 hours.
     * @param playerId Player ID.
     * @return Player entity.
     */
    fun getPlayer(playerId: Long) = playerDao.getPlayer(playerId)

    /**
     * Insert player.
     * @param player Player entity.
     */
    fun insertPlayer(player: PlayerEntity) = playerDao.insertPlayer(player)

    /**
     * Delete old data of players older than 24 hours.
     */
    fun deleteOldData() = playerDao.deleteOldData()
}