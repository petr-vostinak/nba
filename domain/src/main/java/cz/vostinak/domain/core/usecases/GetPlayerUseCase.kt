package cz.vostinak.domain.core.usecases

import cz.vostinak.data.repository.PlayerDetailRepository
import cz.vostinak.domain.core.entities.Player
import cz.vostinak.domain.core.mapper.toDomain
import cz.vostinak.domain.core.mapper.toEntity
import cz.vostinak.room.repository.PlayerDbRepository
import javax.inject.Inject

/**
 * Get player use case.
 */
class GetPlayerUseCase @Inject constructor(
    private val playerApiRepository: PlayerDetailRepository,
    private val playerDbRepository: PlayerDbRepository
) {
    /**
     * Get player by ID.
     * Loads player from database if available, otherwise loads from API and saves to database.
     *
     * @param playerId Player ID.
     * @return Player.
     */
    suspend operator fun invoke(playerId: Long): Player {
        playerDbRepository.getPlayer(playerId)?.let { dbPlayer ->
            return dbPlayer.toDomain()
        } ?: run {
            val apiPlayer = playerApiRepository.getPlayerDetail(playerId)
            playerDbRepository.insertPlayer(apiPlayer.toEntity())
            return apiPlayer.toDomain()
        }
    }
}