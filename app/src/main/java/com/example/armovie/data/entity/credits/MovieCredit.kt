package com.example.armovie.data.entity.credits


import com.google.gson.annotations.SerializedName

data class MovieCredit(
    @SerializedName("cast")
    val cast: List<Cast>,
    //@SerializedName("crew")
    //val crew: List<Crew>,
    @SerializedName("id")
    val id: Int
)