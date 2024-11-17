package cz.vostinak.nba.ui.gui.player.model

import cz.vostinak.nba.api.NbaRestAPI
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

/**
 * Player detail repository.
 */
class PlayerDetailRepository @Inject constructor(
    private val nbaRestApi: NbaRestAPI
) {
    /** Player detail state. */
    val playerDetailState = MutableStateFlow(PlayerDetailState())

    /**
     * Get player detail.
     */
    suspend fun getPlayerDetail(playerId: Long) {
        val player = nbaRestApi.getPlayerDetail(playerId)

        playerDetailState.value = PlayerDetailState(
            isLoading = false,
            player = player.data
        )
    }
}