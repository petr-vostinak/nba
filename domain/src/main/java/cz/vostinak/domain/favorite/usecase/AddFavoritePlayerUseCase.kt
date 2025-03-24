package cz.vostinak.domain.favorite.usecase

import cz.vostinak.domain.favorite.entities.FavoritePlayerItem
import cz.vostinak.domain.favorite.mapper.toEntity
import cz.vostinak.realm.repository.FavoritePlayerRepository
import javax.inject.Inject

/**
 * Add favorite player use case.
 */
class AddFavoritePlayerUseCase @Inject constructor(
    private val repository: FavoritePlayerRepository
) {
    suspend operator fun invoke(player: FavoritePlayerItem) {
        repository.addFavoritePlayer(player.toEntity())
    }
}