package com.example.dogapiproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogapiproject.DogBreed
import com.example.dogapiproject.R

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    private val data: MutableList<DogBreed> = mutableListOf()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val breedName: TextView = itemView.findViewById(R.id.textBreedName)
        val breedGroup: TextView = itemView.findViewById(R.id.textBreedGroup)
        val breedOrigin: TextView = itemView.findViewById(R.id.textBreedOrigin)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_search_result, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dogBreed = data[position]

        holder.breedName.text = dogBreed.name
        holder.breedGroup.text = "Group: ${dogBreed.bred_for}"
        holder.breedOrigin.text = "Origin: ${dogBreed.origin}"
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun submitList(data: List<DogBreed>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }
}