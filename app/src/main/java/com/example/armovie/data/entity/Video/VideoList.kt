package com.example.armovie.data.entity.Video


import com.google.gson.annotations.SerializedName

data class VideoList(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val videoResults: List<VideoResult>
)