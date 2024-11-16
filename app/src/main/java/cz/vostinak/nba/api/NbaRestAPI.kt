package cz.vostinak.nba.api

import cz.vostinak.nba.api.response.PlayersListResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * NBA REST API.
 */
interface NbaRestAPI {

    /**
     * Get list of players.
     * @param cursor Cursor for pagination.
     * @param perPage Number of items per page.
     */
    @GET("/v1/players")
    suspend fun getPlayers(
        @Query("cursor") cursor: Int? = 0,
        @Query("per_page") perPage: Int? = 35
    ): PlayersListResponse
}