package cz.vostinak.domain.usecases

import cz.vostinak.room.repository.PlayerDbRepository
import javax.inject.Inject

/**
 * Delete old data use case.
 */
class DeleteOldDataUseCase @Inject constructor(
    private val playerDbRepository: PlayerDbRepository
) {

    /**
     * Delete old data of players older than 24 hours.
     */
    suspend operator fun invoke() {
        playerDbRepository.deleteOldData()
    }
}