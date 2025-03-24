package cz.vostinak.realm.entity

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

/**
 * Entity representing favorite player.
 */
open class FavoritePlayer(
    /** Unique identifier of the player. */
    @PrimaryKey
    var id: Long,
    /** Name of the player. */
    var name: String,
    /** Team of the player. */
    var team: String,
    /** Image URL of the player. */
    var imageUrl: String
): RealmObject {
    /** Empty constructor for Realm. */
    constructor(): this(0, "", "", "")
}