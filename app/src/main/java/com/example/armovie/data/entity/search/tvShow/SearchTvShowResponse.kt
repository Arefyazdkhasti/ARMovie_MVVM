package com.example.armovie.data.entity.search.tvShow


import com.example.armovie.data.entity.TvShowList.TvShow
import com.google.gson.annotations.SerializedName

data class SearchTvShowResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<TvShow>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)