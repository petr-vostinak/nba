package cz.vostinak.realm.repository

import cz.vostinak.realm.entity.FavoritePlayer
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Repository for favorite players.
 */
class RealmFavoritePlayerRepository(private val realm: Realm): FavoritePlayerRepository {

    /**
     * Adds a favorite player.
     * @param player Player to add
     */
    override suspend fun addFavoritePlayer(player: FavoritePlayer) {
        realm.write {
            copyToRealm(player)
        }
    }

    /**
     * Removes a favorite player.
     * @param playerId ID of the player to remove
     */
    override suspend fun removeFavoritePlayer(playerId: Long) {
        realm.write {
            val player = query<FavoritePlayer>("id == $0", playerId).first().find()
            player?.let { delete(it) }
        }
    }

    /**
     * Gets all favorite players.
     * @return Flow of list of favorite players
     */
    override fun getAllFavoritePlayers(): Flow<List<FavoritePlayer>> {
        return realm.query<FavoritePlayer>().asFlow().map { it.list.toList() }
    }

    /**
     * Checks if a player is a favorite player.
     * @param playerId ID of the player to check
     * @return True if the player is a favorite player, false otherwise
     */
    override suspend fun isFavoritePlayer(playerId: Long): Boolean {
        return realm.query<FavoritePlayer>("id == $0", playerId).first().find() != null
    }
}