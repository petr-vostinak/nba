package cz.vostinak.domain.core.usecases

import cz.vostinak.data.repository.PlayerDetailRepository
import cz.vostinak.domain.core.entities.DataOrigin
import cz.vostinak.domain.core.entities.Player
import cz.vostinak.domain.core.mapper.toDomain
import cz.vostinak.domain.core.mapper.toEntity
import cz.vostinak.room.repository.PlayerDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Get player use case.
 */
class GetPlayerUseCase @Inject constructor(
    private val playerApiRepository: PlayerDetailRepository,
    private val playerDbRepository: PlayerDbRepository
) {

    companion object {
        /** Data expiration limit in milliseconds */
        private const val LIMIT_IN_MILLIS = 24 * 60 * 60 * 1000 // 24 hours
    }

    /**
     * Get player by ID.
     * Loads player from database if available, otherwise loads from API and saves to database.
     *
     * @param playerId Player ID.
     * @return Player.
     */
    operator fun invoke(playerId: Long): Flow<Player> = flow {
        val dbPlayer = playerDbRepository.getPlayer(playerId)
        val expirationTime = System.currentTimeMillis() - LIMIT_IN_MILLIS

        dbPlayer?.let { player ->
            val origin = if(player.timestamp < expirationTime) DataOrigin.DB_EXPIRED else DataOrigin.DB_CURRENT
            emit(player.toDomain(origin))

            if(origin == DataOrigin.DB_EXPIRED) {
                emit(readApiDataAndUpdateDb(playerId))
            }
        } ?: run {
            emit(readApiDataAndUpdateDb(playerId))
        }
    }

    /**
     * Read player data from API and update database.
     * @param playerId Player ID.
     * @return Player.
     */
    private suspend fun readApiDataAndUpdateDb(playerId: Long): Player {
        val apiPlayer = playerApiRepository.getPlayerDetail(playerId)
        playerDbRepository.insertPlayer(apiPlayer.toEntity())
        return apiPlayer.toDomain()
    }
}