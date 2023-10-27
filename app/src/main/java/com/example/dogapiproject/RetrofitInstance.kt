package com.example.dogapiproject

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://api.thedogapi.com/v1/"
    private const val API_KEY = "YOUR_API_KEY"

    private val apiKeyInterceptor = Interceptor { chain ->
        val original = chain.request()
        val request = original.newBuilder()
            .header("x-api-key", API_KEY)
            .method(original.method, original.body)
            .build()
        chain.proceed(request)
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(apiKeyInterceptor)
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

val dogApiService = RetrofitInstance.retrofit.create(DogApiService::class.java)