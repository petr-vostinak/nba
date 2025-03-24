package cz.vostinak.domain.favorite.mapper

import cz.vostinak.domain.favorite.entities.FavoritePlayerItem
import cz.vostinak.realm.entity.FavoritePlayer

/**
 * Convert FavoritePlayer to FavoritePlayerItem.
 * @return FavoritePlayerItem.
 */
fun FavoritePlayer.toItemDomain() = FavoritePlayerItem(
    id = id,
    fullName = name,
    teamFullName = team,
    imageUrl = imageUrl
)

/**
 * Convert FavoritePlayerItem to FavoritePlayer.
 * @return FavoritePlayer.
 */
fun FavoritePlayerItem.toEntity() = FavoritePlayer(
    id = id,
    name = fullName,
    team = teamFullName,
    imageUrl = imageUrl
)