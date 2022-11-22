package com.banibegood.hogentproject.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private val logger = HttpLoggingInterceptor().apply{level = HttpLoggingInterceptor.Level.BASIC}
const val BASE_URL = "https://www.freetogame.com/api/"

//create moshi object
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

interface GameApiService {

    @GET("games")
    fun getData() : Deferred<List<ApiGame>>

    companion object{
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): GameApiService{
            val reqInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url
                    .newBuilder()
                    .build()
                val req = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(req)

            }

            val httpClient = OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor(reqInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(httpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
//                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GameApiService::class.java)

        }
    }
}
