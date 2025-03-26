package cz.vostinak.domain.favorite.di

import cz.vostinak.domain.favorite.usecase.AddFavoritePlayerUseCase
import cz.vostinak.domain.favorite.usecase.GetAllFavoritePlayersUseCase
import cz.vostinak.domain.favorite.usecase.IsFavoritePlayerUseCase
import cz.vostinak.domain.favorite.usecase.RemoveFavoritePlayerUseCase
import cz.vostinak.realm.repository.FavoritePlayerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoritesDataModule {

    @Provides
    @Singleton
    fun provideAddFavoritePlayerUseCase(repository: FavoritePlayerRepository): AddFavoritePlayerUseCase {
        return AddFavoritePlayerUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideRemoveFavoritePlayerUseCase(repository: FavoritePlayerRepository): RemoveFavoritePlayerUseCase {
        return RemoveFavoritePlayerUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetAllFavoritePlayersUseCase(repository: FavoritePlayerRepository): GetAllFavoritePlayersUseCase {
        return GetAllFavoritePlayersUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideIsFavoritePlayerUseCase(repository: FavoritePlayerRepository): IsFavoritePlayerUseCase {
        return IsFavoritePlayerUseCase(repository)
    }
}