package com.example.itunessearch.api

import com.example.itunessearch.models.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface iTunesAPI {
    @GET("search")
    suspend fun getResults(
        @Query("term")
        searchString: String,
        @Query("country")
        countryCode: String="IN",
        @Query("sort")
        sortType: String="recent"
    ): Response<SearchResponse>
}