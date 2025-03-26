package cz.vostinak.realm

import android.content.Context
import cz.vostinak.realm.entity.FavoritePlayer
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

/**
 * Manager for Realm.
 */
object RealmManager {

    /**
     * Gets a Realm instance.
     * @param context Context
     * @return Realm instance
     */
    fun getRealm(context: Context): Realm {
        val config = RealmConfiguration.Builder(schema = setOf(FavoritePlayer::class))
            .name("favorites.realm")
            .schemaVersion(1)
            .build()
        return Realm.open(config)
    }
}