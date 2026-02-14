package com.cordero.it342.mobile

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

data class RegisterRequest(
    val email: String,
    val password: String,
    val confirmPassword: String,
    val firstName: String,
    val lastName: String
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class AuthResponse(
    val id: Long,
    val token: String,
    val tokenType: String?,
    val expiresIn: Long?,
    val email: String,
    val firstName: String,
    val lastName: String
)

data class ErrorResponse(val message: String)

data class UserResponse(
    val id: Long,
    val email: String,
    val firstName: String,
    val lastName: String
)

interface AuthApi {
    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): AuthResponse

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): AuthResponse

    @POST("auth/logout")
    suspend fun logout(@Header("Authorization") bearer: String): ErrorResponse
}

interface UserApi {
    @GET("user/me")
    suspend fun me(@Header("Authorization") bearer: String): UserResponse
}
