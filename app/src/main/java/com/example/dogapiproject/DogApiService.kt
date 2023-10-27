package com.example.dogapiproject

import retrofit2.http.GET
import retrofit2.http.Query

interface DogApiService {
    @GET("breeds")
    suspend fun getBreeds(): List<DogBreed>

    @GET("images/search")
    suspend fun getBreedImages(@Query("breed_id") breedId: String): List<DogBreedImage>

    @GET("breeds/search")
    suspend fun searchBreeds(@Query("q") query: String): List<DogBreed>
}