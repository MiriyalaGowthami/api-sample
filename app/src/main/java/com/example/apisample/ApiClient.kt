package com.example.apisample

import ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/" // Replace with your API base URL

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())  // Convert JSON to Kotlin objects
            .build()
    }

    // This creates the ApiService that we can use to make network requests
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
