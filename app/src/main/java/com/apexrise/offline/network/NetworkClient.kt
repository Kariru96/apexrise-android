package com.apexrise.offline.network

import android.content.Context
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object NetworkClient {
    private var retrofit: Retrofit? = null
    private var tokenManager: TokenManager? = null

    fun initialize(context: Context, baseUrl: String) {
        tokenManager = TokenManager(context)

        val moshi = Moshi.Builder().build()

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(AuthInterceptor(tokenManager!!))
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    fun getApi(): ApexRiseApi {
        return retrofit?.create(ApexRiseApi::class.java)
            ?: throw IllegalStateException("Network client not initialized")
    }

    fun getTokenManager(): TokenManager {
        return tokenManager ?: throw IllegalStateException("Token manager not initialized")
    }
}
