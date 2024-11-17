package cz.vostinak.nba.ui.gui.list.model

import cz.vostinak.nba.api.NbaPlayersRestAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * Players list repository.
 * @param nbaPlayersRestApi NBA REST API.
 */
class PlayersListRepository @Inject constructor(
    private val nbaPlayersRestApi: NbaPlayersRestAPI
) {
    /** Players list state. */
    val playersListState = MutableStateFlow(PlayersListState())

    /** Paging cursor */
    private var cursor = 0

    /**
     * Initial load of players.
     */
    suspend fun initLoadPlayers() {
        playersListState.update {
            it.copy(isLoading = true)
        }

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
        val playersListResponse = nbaPlayersRestApi.getPlayers(cursor = offset)
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