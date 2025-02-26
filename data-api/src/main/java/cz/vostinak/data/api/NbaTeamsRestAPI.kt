package cz.vostinak.data.api

import cz.vostinak.data.api.response.TeamDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * NBA teams REST API.
 */
interface NbaTeamsRestAPI {

    /**
     * Get NBA team by ID.
     * @return Detail of NBA team.
     */
    @GET("/v1/teams/{teamId}")
    suspend fun getTeam(
        @Path("teamId") teamId: Long
    ): TeamDetailResponse
}