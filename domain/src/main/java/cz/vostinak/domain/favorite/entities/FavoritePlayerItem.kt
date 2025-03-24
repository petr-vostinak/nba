package cz.vostinak.domain.favorite.entities

/**
 * Favorite player item.
 */
data class FavoritePlayerItem(
    /** Player id. */
    val id: Long,
    /** Player full name. */
    val fullName: String,
    /** Player team full name. */
    val teamFullName: String,
    /** Photo URL. */
    val imageUrl: String
)
