package cz.vostinak.room.di

import android.content.Context
import androidx.room.Room
import cz.vostinak.room.AppDatabase
import cz.vostinak.room.player.PlayerDao
import cz.vostinak.room.team.TeamDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext app: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context =  app,
            klass = AppDatabase::class.java,
            name = "database-name"
        ).build()
    }

    @Provides
    @Singleton
    fun providePlayerDao(db: AppDatabase): PlayerDao {
        return db.playerDao()
    }

    @Provides
    @Singleton
    fun provideTeamDao(db: AppDatabase): TeamDao {
        return db.teamDao()
    }

}