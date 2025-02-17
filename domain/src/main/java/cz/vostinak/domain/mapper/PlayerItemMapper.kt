package cz.vostinak.domain.mapper

import cz.vostinak.data.api.to.PlayerTO
import cz.vostinak.domain.entities.PlayerItem

fun PlayerTO.toItemDomain() = PlayerItem(
    id = id,
    fullName = "$firstName $lastName",
    teamFullName = team?.fullName ?: "",
    jerseyNumber = jerseyNumber ?: ""
)