package com.example.armovie.data.entity.movieList


import com.google.gson.annotations.SerializedName

data class movieList(
    val page: Int,
    val results : List<movieItem>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResult: Int
)
