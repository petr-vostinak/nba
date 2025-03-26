package cz.vostinak.room

import androidx.room.Database
import androidx.room.RoomDatabase
import cz.vostinak.room.player.PlayerDao
import cz.vostinak.room.player.PlayerEntity
import cz.vostinak.room.team.TeamDao
import cz.vostinak.room.team.TeamEntity

/**
 * Application database.
 */
@Database(entities = [PlayerEntity::class, TeamEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    /**
     * Player DAO.
     */
    abstract fun playerDao(): PlayerDao

    /**
     * Team DAO.
     */
    abstract fun teamDao(): TeamDao
}