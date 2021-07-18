package com.example.armovie.data.network.response

import androidx.lifecycle.LiveData
import com.example.armovie.data.entity.list.movieList

interface MovieNetworkDataSource {
    val movieList : LiveData<movieList>

    suspend fun fetchMovieList(
        type:String
    )

}