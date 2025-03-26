package cz.vostinak.room.repository

import cz.vostinak.room.team.TeamDao
import cz.vostinak.room.team.TeamEntity
import javax.inject.Inject

/**
 * Team database repository.
 */
class TeamDbRepository @Inject constructor(
    private val teamDao: TeamDao
) {

    /**
     * Get team by ID if there is a database record not older than 24 hours.
     * @param teamId Team ID.
     * @return Team entity.
     */
    fun getTeam(teamId: Long) = teamDao.getTeam(teamId)

    /**
     * Insert team.
     * @param team Team entity.
     */
    fun insertTeam(team: TeamEntity) = teamDao.insertTeam(team)
}