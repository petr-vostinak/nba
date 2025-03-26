package cz.vostinak.domain.core.usecases

import cz.vostinak.data.repository.TeamDetailRepository
import cz.vostinak.domain.core.entities.DataOrigin
import cz.vostinak.domain.core.entities.Team
import cz.vostinak.domain.core.mapper.toDomain
import cz.vostinak.domain.core.mapper.toEntity
import cz.vostinak.room.repository.TeamDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Get team use case.
 */
class GetTeamUseCase @Inject constructor(
    private val teamApiRepository: TeamDetailRepository,
    private val teamDbRepository: TeamDbRepository,
    private val getTeamLogoUseCase: GetTeamLogoUseCase
) {

    companion object {
        /** Data expiration limit in milliseconds */
        private const val LIMIT_IN_MILLIS = 24 * 60 * 60 * 1000 // 24 hours
    }

    /**
     * Get team by ID.
     *
     * @param teamId Team ID.
     * @return Team.
     */
    operator fun invoke(teamId: Long): Flow<Team> = flow {
        val dbTeam = teamDbRepository.getTeam(teamId)
        val expirationTime = System.currentTimeMillis() - LIMIT_IN_MILLIS

        dbTeam?.let { team ->
            val origin = if(team.timestamp < expirationTime) DataOrigin.DB_EXPIRED else DataOrigin.DB_CURRENT
            val data = team.toDomain(origin).copy(
                logoResourceIdRes = getTeamLogoUseCase(teamApiRepository.getTeamDetail(teamId).abbreviation)
            )
            emit(data)

            if(origin == DataOrigin.DB_EXPIRED) {
                emit(readApiDataAndUpdateDb(teamId))
            }
        } ?: run {
            emit(readApiDataAndUpdateDb(teamId))
        }
    }

    /**
     * Read team data from API and update database.
     * @param teamId Team ID.
     * @return Team.
     */
    private suspend fun readApiDataAndUpdateDb(teamId: Long): Team {
        val apiTeam = teamApiRepository.getTeamDetail(teamId)
        teamDbRepository.insertTeam(apiTeam.toEntity())
        val data = apiTeam.toDomain().copy(
            logoResourceIdRes = getTeamLogoUseCase(teamApiRepository.getTeamDetail(teamId).abbreviation)
        )
        return data
    }
}