package cz.vostinak.domain.core.usecases

import cz.vostinak.data.repository.TeamDetailRepository
import cz.vostinak.domain.core.entities.Team
import cz.vostinak.domain.core.mapper.toDomain
import javax.inject.Inject

/**
 * Get team use case.
 */
class GetTeamUseCase @Inject constructor(
    private val teamRepository: TeamDetailRepository,
    private val getTeamLogoUseCase: GetTeamLogoUseCase
) {

    /**
     * Get team by ID.
     *
     * @param teamId Team ID.
     * @return Team.
     */
    suspend operator fun invoke(teamId: Long): Team {
        return teamRepository.getTeamDetail(teamId).toDomain().copy(
            logoResourceIdRes = getTeamLogoUseCase(teamRepository.getTeamDetail(teamId).abbreviation)
        )
    }
}