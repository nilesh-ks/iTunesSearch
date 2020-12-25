package com.example.itunessearch.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itunessearch.MainActivity
import com.example.itunessearch.R
import com.example.itunessearch.adapters.SearchAdapter
import com.example.itunessearch.util.Resource


class SearchFragment : Fragment(R.layout.fragment_search) {
    lateinit var viewModel: SearchViewModel
    lateinit var searchAdapter: SearchAdapter
    private lateinit var rvSearch: RecyclerView
    lateinit var progressBar: ProgressBar
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=(activity as MainActivity).viewModel
        rvSearch=view.findViewById(R.id.rvSearch)
        setUpRecyclerView()

        viewModel.songs.observe(viewLifecycleOwner, Observer {response->
            when(response){
                is Resource.Success-> {
                    hideProgressBar()
                    response.data?.let { searchResponse ->
                        searchAdapter.differ.submitList(searchResponse.results)
                    }
                }
                is Resource.Error->{
                    hideProgressBar()
                    response.message?.let {message->
                        Log.e("SearchFragment", "An error occured: $message")
                    }
                }
                is Resource.Loading->{
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar(){
        progressBar.visibility=View.INVISIBLE

    }
    private fun showProgressBar(){
        progressBar.visibility=View.VISIBLE

    }
    private fun setUpRecyclerView()
    {
        searchAdapter= SearchAdapter()
        rvSearch.apply {
            adapter= searchAdapter
            layoutManager= LinearLayoutManager(activity)
        }

    }






}