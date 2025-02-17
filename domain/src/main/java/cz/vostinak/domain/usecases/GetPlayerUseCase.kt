package cz.vostinak.domain.usecases

import cz.vostinak.data.repository.PlayerDetailRepository
import cz.vostinak.domain.entities.Player
import cz.vostinak.domain.mapper.toDomain
import javax.inject.Inject

/**
 * Get player use case.
 */
class GetPlayerUseCase @Inject constructor(
    private val playerRepository: PlayerDetailRepository
) {
    /**
     * Get player by ID.
     *
     * @param playerId Player ID.
     * @return Player.
     */
    suspend operator fun invoke(playerId: Long): Player {
        return playerRepository.getPlayerDetail(playerId).toDomain()
    }
}