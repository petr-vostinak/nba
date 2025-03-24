package cz.vostinak.realm.di

import android.content.Context
import cz.vostinak.realm.RealmManager
import cz.vostinak.realm.repository.FavoritePlayerRepository
import cz.vostinak.realm.repository.RealmFavoritePlayerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoritesDataModule {

    @Provides
    @Singleton
    fun provideRealm(@ApplicationContext context: Context): Realm {
        return RealmManager.getRealm(context)
    }

    @Provides
    @Singleton
    fun provideFavoritePlayerRepository(realm: Realm): FavoritePlayerRepository {
        return RealmFavoritePlayerRepository(realm)
    }
}