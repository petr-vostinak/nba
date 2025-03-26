package cz.vostinak.domain.core.mapper

import cz.vostinak.data.api.to.PlayerTO
import cz.vostinak.domain.core.entities.PlayerItem

/**
 * Convert PlayerTO to PlayerItem.
 * @return PlayerItem.
 */
fun PlayerTO.toItemDomain() = PlayerItem(
    id = id,
    fullName = "$firstName $lastName",
    teamFullName = team?.fullName ?: "---",
    jerseyNumber = jerseyNumber ?: "NA"
)