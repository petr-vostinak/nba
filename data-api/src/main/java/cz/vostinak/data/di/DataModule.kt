package cz.vostinak.data.di

import com.google.gson.GsonBuilder
import cz.vostinak.data.BuildConfig
import cz.vostinak.data.api.NbaPlayersRestAPI
import cz.vostinak.data.api.NbaTeamsRestAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun nbaPlayersApi(
        @MainRetrofit retrofit: Retrofit
    ): NbaPlayersRestAPI {
        return retrofit.create(NbaPlayersRestAPI::class.java)
    }

    @Singleton
    @Provides
    fun nbaTeamsApi(
        @MainRetrofit retrofit: Retrofit
    ): NbaTeamsRestAPI {
        return retrofit.create(NbaTeamsRestAPI::class.java)
    }

    // ----------------------------------------- Base URL ------------------------------------------

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class BaseUrl

    @Provides
    @BaseUrl
    fun provideBaseUrl() = "https://api.balldontlie.io"

    // ----------------------------------- GsonConverterFactory ------------------------------------

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class GsonConverter

    @Singleton
    @Provides
    @GsonConverter
    fun provideGsonConverter(): GsonConverterFactory {
        return GsonConverterFactory.create(
            GsonBuilder()
                .create()
        )
    }

    // ----------------------------------------- Retrofit ------------------------------------------

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class MainRetrofit

    @Singleton
    @Provides
    @MainRetrofit
    fun provideRetrofit(
        @BaseUrl baseUrl: String,
        @GsonConverter gsonConverter: GsonConverterFactory,
        @OkHttpClient httpClient: okhttp3.OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverter)
            .client(httpClient)
            .build()
    }

    // --------------------------------------- Http Client -----------------------------------------

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class OkHttpClient

    @Singleton
    @Provides
    @OkHttpClient
    fun provideHttpClient(): okhttp3.OkHttpClient {
        val httpClientBuilder = okhttp3.OkHttpClient.Builder()

        httpClientBuilder.addInterceptor(Interceptor { chain ->
            val builder = chain.request().newBuilder()

            val request = builder
                .header("Authorization", BuildConfig.API_KEY)
                .build()

            val response = chain.withReadTimeout(30, TimeUnit.SECONDS).proceed(request)
            response
        })

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClientBuilder.addInterceptor(loggingInterceptor)

        httpClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
        httpClientBuilder.readTimeout(30, TimeUnit.SECONDS)
        httpClientBuilder.writeTimeout(30, TimeUnit.SECONDS)

        return httpClientBuilder.build()
    }

}