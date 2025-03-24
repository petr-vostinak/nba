package cz.vostinak.domain.favorite.usecase

import cz.vostinak.domain.favorite.entities.FavoritePlayerItem
import cz.vostinak.domain.favorite.mapper.toItemDomain
import cz.vostinak.realm.repository.FavoritePlayerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Get all favorite players use case.
 */
class GetAllFavoritePlayersUseCase @Inject constructor(
    private val repository: FavoritePlayerRepository
) {
    operator fun invoke(): Flow<List<FavoritePlayerItem>> {
        return repository.getAllFavoritePlayers().map {
            it.map { it.toItemDomain() }
        }
    }
}