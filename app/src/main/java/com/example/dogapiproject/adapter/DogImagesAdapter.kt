package com.example.dogapiproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogapiproject.DogBreed
import com.example.dogapiproject.DogBreedImage
import com.example.dogapiproject.R
import com.squareup.picasso.Picasso

class DogImagesAdapter : RecyclerView.Adapter<DogImagesAdapter.ViewHolder>() {
    private val data: MutableList<Pair<DogBreed, DogBreedImage>> = mutableListOf()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val breedName: TextView = itemView.findViewById(R.id.textBreedName)
        val breedImage: ImageView = itemView.findViewById(R.id.imageViewBreed)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_dog_breed, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (dogBreed, dogBreedImage) = data[position]

        holder.breedName.text = dogBreed.name

        Picasso.get()
            .load(dogBreedImage.url)
            .placeholder(R.drawable.ic_android_black_24dp)
            .into(holder.breedImage)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun submitList(data: List<Pair<DogBreed, DogBreedImage>>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }
}