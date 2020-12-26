package com.example.itunessearch.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itunessearch.MainActivity
import com.example.itunessearch.R
import com.example.itunessearch.adapters.SearchAdapter


class SavedFragment : Fragment(R.layout.fragment_saved) {
    lateinit var viewModel: SearchViewModel
    lateinit var searchAdapter: SearchAdapter
    private lateinit var rvSavedNews: RecyclerView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=(activity as MainActivity).viewModel
        rvSavedNews = view.findViewById(R.id.rvFavourite)
        setUpRecyclerView()
        searchAdapter.setOnItemClickListener {
            val bundle=Bundle().apply {
                putSerializable("article",it)

            }
            findNavController().navigate(
                    R.id.action_savedFragment_to_songDescFragment,
                    bundle
            )
        }

    }
    private fun setUpRecyclerView()
    {
        searchAdapter= SearchAdapter()
        rvSavedNews.apply {
            adapter= searchAdapter
            layoutManager= LinearLayoutManager(activity)
        }

    }






}