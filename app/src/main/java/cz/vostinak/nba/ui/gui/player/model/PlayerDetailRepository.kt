package cz.vostinak.nba.ui.gui.player.model

import cz.vostinak.nba.api.NbaPlayersRestAPI
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

/**
 * Player detail repository.
 */
class PlayerDetailRepository @Inject constructor(
    private val nbaPlayersRestApi: NbaPlayersRestAPI
) {
    /** Player detail state. */
    val playerDetailState = MutableStateFlow(PlayerDetailState())

    /**
     * Get player detail.
     */
    suspend fun getPlayerDetail(playerId: Long) {
        try {
            val player = nbaPlayersRestApi.getPlayerDetail(playerId)

            playerDetailState.value = PlayerDetailState(
                isLoading = false,
                player = player.data
            )
        } catch (e: Exception) {
            playerDetailState.value = PlayerDetailState(
                player = null,
                isLoading = false,
                error = e
            )
        }
    }
}