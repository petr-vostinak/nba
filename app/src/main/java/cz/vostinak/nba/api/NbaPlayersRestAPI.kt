package cz.vostinak.nba.api

import cz.vostinak.nba.api.response.PlayerDetailResponse
import cz.vostinak.nba.api.response.PlayersListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * NBA players REST API.
 */
interface NbaPlayersRestAPI {

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

    /**
     * Get player detail.
     * @param playerId Player ID.
     */
    @GET("/v1/players/{playerId}")
    suspend fun getPlayerDetail(
        @Path("playerId") playerId: Long
    ): PlayerDetailResponse
}