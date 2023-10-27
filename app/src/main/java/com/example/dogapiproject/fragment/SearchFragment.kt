package com.example.dogapiproject.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogapiproject.R
import com.example.dogapiproject.adapter.SearchAdapter
import com.example.dogapiproject.viewmodel.DogViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private lateinit var viewModel: DogViewModel
    private lateinit var searchAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        val searchView: SearchView = view.findViewById(R.id.searchView)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewSearch)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        searchAdapter = SearchAdapter()
        recyclerView.adapter = searchAdapter

        viewModel = ViewModelProvider(this).get(DogViewModel::class.java)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    // Search by breed name
                    GlobalScope.launch {
                        val searchResults = viewModel.searchBreeds(query)
                        requireActivity().runOnUiThread {
                            searchAdapter.submitList(searchResults)
                        }
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Handle search text change
                return true
            }
        })

        return view
    }
}