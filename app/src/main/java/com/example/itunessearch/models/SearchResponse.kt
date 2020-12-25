package com.example.itunessearch.models


import com.example.itunessearch.models.Result
import com.google.gson.annotations.SerializedName

data class SearchResponse(
        val resultCount: Int,
        val results: List<Result>
)