package com.example.itunessearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.itunessearch.db.ResultDatabase
import com.example.itunessearch.repository.SearchRepository
import com.example.itunessearch.ui.fragments.SearchViewModel
import com.example.itunessearch.ui.fragments.SearchViewModelProviderFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: SearchViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navigationController = findNavController(R.id.mainNavHostFragment)

        val songRepository=SearchRepository(ResultDatabase(this))
        val viewModelProviderFactory=SearchViewModelProviderFactory(songRepository)
        viewModel=ViewModelProvider(this, viewModelProviderFactory).get(SearchViewModel::class.java)
        bottomNavigationView.setupWithNavController(navigationController)


    }
}