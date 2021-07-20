package com.example.armovie.data.entity.list


import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class movieList(
    val page: Int,
    val results : List<movieItem>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResult: Int
)
