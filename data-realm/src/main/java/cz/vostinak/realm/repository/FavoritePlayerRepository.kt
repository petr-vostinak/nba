package cz.vostinak.realm.repository

import cz.vostinak.realm.entity.FavoritePlayer
import kotlinx.coroutines.flow.Flow

/**
 * Repository for favorite players.
 */
interface FavoritePlayerRepository {
    /**
     * Adds a favorite player.
     * @param player Player to add
     */
    suspend fun addFavoritePlayer(player: FavoritePlayer)

    /**
     * Removes a favorite player.
     * @param playerId ID of the player to remove
     */
    suspend fun removeFavoritePlayer(playerId: Long)

    /**
     * Gets all favorite players.
     * @return Flow of list of favorite players
     */
    fun getAllFavoritePlayers(): Flow<List<FavoritePlayer>>

    /**
     * Checks if a player is a favorite player.
     * @param playerId ID of the player to check
     * @return True if the player is a favorite player, false otherwise
     */
    suspend fun isFavoritePlayer(playerId: Long): Boolean
}