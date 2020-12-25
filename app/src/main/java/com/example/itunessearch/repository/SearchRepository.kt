package com.example.itunessearch.repository

import com.example.itunessearch.api.RetrofitInstance
import com.example.itunessearch.db.ResultDatabase
import com.example.itunessearch.models.SearchResponse
import retrofit2.Response

class SearchRepository(
    val db: ResultDatabase
){
    suspend fun searchITune(searchQuery: String): Response<SearchResponse> {
        return RetrofitInstance.api.getResults(searchQuery)
    }
}
