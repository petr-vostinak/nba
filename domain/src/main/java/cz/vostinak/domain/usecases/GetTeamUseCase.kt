package cz.vostinak.domain.usecases

import cz.vostinak.data.repository.TeamDetailRepository
import cz.vostinak.domain.entities.Team
import cz.vostinak.domain.mapper.toDomain
import javax.inject.Inject

/**
 * Get team use case.
 */
class GetTeamUseCase @Inject constructor(
    private val teamRepository: TeamDetailRepository
) {

    /**
     * Get team by ID.
     *
     * @param teamId Team ID.
     * @return Team.
     */
    suspend operator fun invoke(teamId: Long): Team {
        return teamRepository.getTeamDetail(teamId).toDomain()
    }
}