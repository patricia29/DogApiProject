package com.example.dogapiproject.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogapiproject.DogBreed
import com.example.dogapiproject.DogBreedImage
import com.example.dogapiproject.R
import com.example.dogapiproject.adapter.DogImagesAdapter
import com.example.dogapiproject.viewmodel.DogViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DogImageFragment  : Fragment() {

    private lateinit var viewModel: DogViewModel
    private lateinit var dogImagesAdapter: DogImagesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dog_images, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewDogImages)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        dogImagesAdapter = DogImagesAdapter()
        recyclerView.adapter = dogImagesAdapter

        viewModel = ViewModelProvider(this).get(DogViewModel::class.java)

        // Fetch dog breeds and images
        GlobalScope.launch {
            val dogBreeds = viewModel.getDogBreeds()
            val breedNames = dogBreeds.map { it.name }
            val breedImages = viewModel.getBreedImages(breedNames)

            val sortedDogBreeds = dogBreeds.sortedBy { it.name }
            val breedDataList = mutableListOf<Pair<DogBreed, DogBreedImage>>()

            for (breed in sortedDogBreeds) {
                val image = breedImages.find { it.id == breed.id }
                if (image != null) {
                    breedDataList.add(Pair(breed, image))
                }
            }

            requireActivity().runOnUiThread {
                dogImagesAdapter.submitList(breedDataList)
            }
        }

        return view
    }
}