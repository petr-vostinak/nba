package cz.vostinak.nba.ui.gui.list.model

import cz.vostinak.nba.api.NbaRestAPI
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

/**
 * Players list repository.
 * @param nbaRestApi NBA REST API.
 */
class PlayersListRepository @Inject constructor(
    private val nbaRestApi: NbaRestAPI
) {
    /** Players list state. */
    val playersListState = MutableStateFlow(PlayersListState())

    /** Paging cursor */
    private var cursor = 0

    /**
     * Initial load of players.
     */
    suspend fun initLoadPlayers() {
        // reset cursor
        cursor = 0

        // set initial loading state
        playersListState.value = playersListState.value.copy(
            players = emptyList(),
            isLoading = true,
            error = null
        )

        // get players
        getPlayersListPage(cursor)
    }

    /**
     * Load next page of players.
     */
    suspend fun loadNexPage() {
        // set loading state
        playersListState.value = playersListState.value.copy(isLoading = true)

        // get players
        getPlayersListPage(cursor)
    }

    /**
     * Get players list page.
     * @param offset Offset.
     */
    private suspend fun getPlayersListPage(offset: Int) {
        val playersListResponse = nbaRestApi.getPlayers(cursor = offset)
        cursor = playersListResponse.meta.nextCursor

        // get old list
        val newPlayersList = playersListState.value.players.toMutableList()
        // add new players
        newPlayersList.addAll(playersListResponse.data)

        playersListState.value = playersListState.value.copy(
            players = newPlayersList,
            isLoading = false
        )
    }
}