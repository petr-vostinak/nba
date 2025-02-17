package cz.vostinak.domain.usecases

import cz.vostinak.data.repository.PlayersListRepository
import cz.vostinak.domain.entities.PlayerItem
import cz.vostinak.domain.mapper.toItemDomain
import javax.inject.Inject

/**
 * Get players list use case.
 */
class GetPlayersListUseCase @Inject constructor(
    private val playersListRepository: PlayersListRepository
) {

    /**
     * Get players list.
     * @return List of players.
     */
    suspend operator fun invoke(): List<PlayerItem> {
        return playersListRepository.initLoadPlayers().map {
            it.toItemDomain()
        }
    }

    /**
     * Load next page of players.
     * @return List of players.
     */
    suspend fun loadNextPage(): List<PlayerItem> {
        return playersListRepository.loadNexPage().map {
            it.toItemDomain()
        }
    }
}