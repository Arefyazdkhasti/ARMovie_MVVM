package com.example.armovie.data.entity.Person


import com.google.gson.annotations.SerializedName

data class CombinedCredit(
    @SerializedName("cast")
    val cast: List<Cast>,
    @SerializedName("crew")
    val crew: List<Crew>,
    @SerializedName("id")
    val id: Int
)