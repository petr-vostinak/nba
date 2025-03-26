package cz.vostinak.data.repository

import cz.vostinak.data.api.NbaPlayersRestAPI
import cz.vostinak.data.api.to.PlayerTO
import javax.inject.Inject

/**
 * Players list repository.
 * @param nbaPlayersRestApi NBA REST API.
 */
class PlayersListRepository @Inject constructor(
    private val nbaPlayersRestApi: NbaPlayersRestAPI
) {
    /** Paging cursor */
    private var cursor = 0

    /**
     * Initial load of players.
     */
    suspend fun initLoadPlayers(): List<PlayerTO> {
        // reset cursor
        cursor = 0

        return getPlayersListPage()
    }

    /**
     * Load next page of players.
     */
    suspend fun loadNexPage(): List<PlayerTO> {
        return getPlayersListPage()
    }

    /**
     * Get players list page.
     */
    private suspend fun getPlayersListPage(): List<PlayerTO> {
        try {
            val playersListResponse = nbaPlayersRestApi.getPlayers(cursor = cursor)
            cursor = playersListResponse.meta.nextCursor

            return playersListResponse.data
        } catch (e: Exception) {
            throw e
        }
    }
}