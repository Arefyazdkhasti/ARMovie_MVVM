package com.example.armovie.data.entity.trending


import com.google.gson.annotations.SerializedName

data class TrendingList(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val trendingItems: List<TrendingItem>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)