package com.example.apisample

import ApiService

class LoginRepository(private val apiService: ApiService) {

    // Function to login
    suspend fun login(email: String, password: String): Result<Boolean> {
        return try {
            val response = apiService.login(email, password)
            if (response.isSuccessful) {
                Result.success(true) // Assume successful login returns a status
            } else {
                Result.failure(Exception("Login failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

