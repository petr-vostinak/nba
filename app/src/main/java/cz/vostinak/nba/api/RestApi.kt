package cz.vostinak.nba.api

import com.google.gson.GsonBuilder
import cz.vostinak.nba.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Rest API object.
 */
object RestApi {
    /** Shared okHttp client instance */
    private var httpClientField: OkHttpClient? = null

    /** Sync lock for okHttp client */
    private val httpClientLock = Any()

    private var gsonConverterField: GsonConverterFactory? = null
    /** Gson converter */
    private val gsonConverter: GsonConverterFactory
        get() {
            gsonConverterField = gsonConverterField ?: GsonConverterFactory.create(
                GsonBuilder()
                    .create()
            )
            return gsonConverterField!!
        }

    @JvmStatic
    val httpClient: OkHttpClient
        get() {
            synchronized(httpClientLock) {

                httpClientField?.let {
                    return it
                }

                val httpClientBuilder = OkHttpClient.Builder()

                httpClientBuilder.addInterceptor( Interceptor { chain ->
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

                httpClientBuilder.build().let {
                    httpClientField = it
                    return it
                }
            }
        }

    @JvmStatic
    val restAdapter: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(RestApiModule.provideBaseUrl())
            .addConverterFactory(gsonConverter)
            .client(httpClient)
            .build()
}