package com.apexrise.offline.ui.screens

import android.content.Context
import androidx.lifecycle.ViewModel
import com.apexrise.offline.network.LoginRequest
import com.apexrise.offline.network.NetworkClient
import com.apexrise.offline.network.RegisterRequest

class AuthViewModel : ViewModel() {

    suspend fun login(email: String, password: String): Boolean {
        return try {
            val api = NetworkClient.getApi()
            val response = api.login(LoginRequest(email, password))
            
            val tokenManager = NetworkClient.getTokenManager()
            tokenManager.saveToken(response.token, response.userId, response.email)
            
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun register(email: String, password: String, farmName: String): Boolean {
        return try {
            val api = NetworkClient.getApi()
            val response = api.register(RegisterRequest(email, password, farmName))
            
            val tokenManager = NetworkClient.getTokenManager()
            tokenManager.saveToken(response.token, response.userId, response.email)
            
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun logout() {
        try {
            val tokenManager = NetworkClient.getTokenManager()
            tokenManager.clearToken()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
