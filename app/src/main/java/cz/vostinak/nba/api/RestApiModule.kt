package cz.vostinak.nba.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RestApiModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class BaseUrl

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class OkHttpClient

    @Provides
    @BaseUrl
    fun provideBaseUrl() = "https://api.balldontlie.io"

    @Singleton
    @Provides
    @OkHttpClient
    fun provideOkHttpClient(): okhttp3.OkHttpClient = RestApi.httpClient

    @Singleton
    @Provides
    fun nbaApi(): NbaRestAPI {
        return RestApi.restAdapter.create(NbaRestAPI::class.java)
    }
}