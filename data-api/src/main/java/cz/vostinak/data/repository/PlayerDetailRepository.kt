package cz.vostinak.data.repository

import cz.vostinak.data.api.NbaPlayersRestAPI
import cz.vostinak.data.api.to.PlayerTO
import javax.inject.Inject

/**
 * PlayerItemState detail repository.
 */
class PlayerDetailRepository @Inject constructor(
    private val nbaPlayersRestApi: NbaPlayersRestAPI
) {
    /**
     * Get player detail.
     */
    suspend fun getPlayerDetail(playerId: Long): PlayerTO {
        try {
            return nbaPlayersRestApi.getPlayerDetail(playerId).data
        } catch (e: Exception) {
            throw e
        }
    }
}