package com.example.armovie.data.entity.search


import com.example.armovie.data.entity.movieList.movieItem
import com.google.gson.annotations.SerializedName

data class SearchMovie(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val searchResults: List<movieItem>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)