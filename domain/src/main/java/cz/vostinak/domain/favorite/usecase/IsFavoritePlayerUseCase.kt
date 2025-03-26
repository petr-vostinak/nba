package cz.vostinak.domain.favorite.usecase

import cz.vostinak.realm.repository.FavoritePlayerRepository
import javax.inject.Inject

/**
 * Check if player is favorite use case.
 */
class IsFavoritePlayerUseCase @Inject constructor(
    private val repository: FavoritePlayerRepository
) {
    suspend operator fun invoke(playerId: Long): Boolean {
        return repository.isFavoritePlayer(playerId)
    }
}