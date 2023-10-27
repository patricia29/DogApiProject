package com.example.dogapiproject.viewmodel

import androidx.lifecycle.ViewModel
import com.example.dogapiproject.DogBreed
import com.example.dogapiproject.DogBreedImage
import com.example.dogapiproject.dogApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DogViewModel  : ViewModel() {
    suspend fun getDogBreeds(): List<DogBreed> {
        return withContext(Dispatchers.IO) {
            try {
                return@withContext dogApiService.getBreeds()
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext emptyList()
            }
        }
    }

    suspend fun getBreedImages(breedIds: List<String>): List<DogBreedImage> {
        val images = mutableListOf<DogBreedImage>()
        for (breedId in breedIds) {
            try {
                val imageList = dogApiService.getBreedImages(breedId)
                if (imageList.isNotEmpty()) {
                    images.add(imageList[0])
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return images
    }

    suspend fun searchBreeds(query: String): List<DogBreed> {
        return withContext(Dispatchers.IO) {
            try {
                return@withContext dogApiService.searchBreeds(query)
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext emptyList()
            }
        }
    }
}