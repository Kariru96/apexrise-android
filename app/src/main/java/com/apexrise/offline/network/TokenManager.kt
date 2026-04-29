package com.apexrise.offline.network

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import okhttp3.Interceptor
import okhttp3.Response

private val Context.tokenDataStore by preferencesDataStore(name = "auth_tokens")

class TokenManager(private val context: Context) {
    companion object {
        private val JWT_TOKEN_KEY = stringPreferencesKey("jwt_token")
        private val USER_ID_KEY = stringPreferencesKey("user_id")
        private val USER_EMAIL_KEY = stringPreferencesKey("user_email")
    }

    suspend fun saveToken(token: String, userId: String, email: String) {
        context.tokenDataStore.edit { preferences ->
            preferences[JWT_TOKEN_KEY] = token
            preferences[USER_ID_KEY] = userId
            preferences[USER_EMAIL_KEY] = email
        }
    }

    suspend fun getToken(): String? {
        return context.tokenDataStore.data
            .map { preferences ->
                preferences[JWT_TOKEN_KEY]
            }
            .first()
    }

    suspend fun getUserId(): String? {
        return context.tokenDataStore.data
            .map { preferences ->
                preferences[USER_ID_KEY]
            }
            .first()
    }

    suspend fun getUserEmail(): String? {
        return context.tokenDataStore.data
            .map { preferences ->
                preferences[USER_EMAIL_KEY]
            }
            .first()
    }

    suspend fun clearToken() {
        context.tokenDataStore.edit { preferences ->
            preferences.clear()
        }
    }

    suspend fun isLoggedIn(): Boolean {
        return getToken() != null
    }
}

class AuthInterceptor(private val tokenManager: TokenManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // Add token to request if available
        val token = kotlin.runBlocking {
            tokenManager.getToken()
        }

        val requestBuilder = originalRequest.newBuilder()
        token?.let {
            requestBuilder.header("Authorization", "Bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }
}
