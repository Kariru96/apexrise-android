package com.apexrise.offline.network

import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApexRiseApi {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): LoginResponse

    @POST("sync")
    suspend fun syncData(
        @Header("Authorization") token: String,
        @Body request: SyncRequest
    ): SyncResponse

    @POST("sync/resolve-conflict")
    suspend fun resolveConflict(
        @Header("Authorization") token: String,
        @Body resolution: ConflictResolution
    ): ResolutionResponse
}
