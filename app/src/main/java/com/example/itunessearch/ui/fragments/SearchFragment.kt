package com.example.itunessearch.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itunessearch.MainActivity
import com.example.itunessearch.R
import com.example.itunessearch.adapters.SearchAdapter
import com.example.itunessearch.util.Constants.Companion.SEARCH_SONG_TIME_DELAY
import com.example.itunessearch.util.Resource
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SearchFragment : Fragment(R.layout.fragment_search) {
    lateinit var viewModel: SearchViewModel
    lateinit var searchAdapter: SearchAdapter
    private lateinit var rvSearch: RecyclerView
    lateinit var progressBar: ProgressBar
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=(activity as MainActivity).viewModel
        progressBar=view.findViewById(R.id.searchProgressBar)
        val etSearch = view.findViewById<TextInputEditText>(R.id.etSearch)
        rvSearch=view.findViewById(R.id.rvSearch)
        setUpRecyclerView()

        searchAdapter.setOnItemClickListener {
            val bundle= Bundle().apply {
                putSerializable("song",it)
            }
            findNavController().navigate(R.id.action_searchFragment_to_songDescFragment,bundle)
        }
        var job: Job?=null
        etSearch.addTextChangedListener{editable->
            job?.cancel()
            job= MainScope().launch {
                delay(SEARCH_SONG_TIME_DELAY)
                editable?.let {

                        viewModel.searchITunes(editable.toString())

                }
            }
        }

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