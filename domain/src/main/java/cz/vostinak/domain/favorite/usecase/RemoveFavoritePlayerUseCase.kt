package cz.vostinak.domain.favorite.usecase

import cz.vostinak.realm.repository.FavoritePlayerRepository
import javax.inject.Inject

/**
 * Remove favorite player use case.
 */
class RemoveFavoritePlayerUseCase @Inject constructor(
    private val repository: FavoritePlayerRepository
) {
    suspend operator fun invoke(playerId: Long) {
        repository.removeFavoritePlayer(playerId)
    }
}