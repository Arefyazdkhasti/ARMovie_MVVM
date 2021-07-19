package com.example.armovie.data.network.response

import androidx.lifecycle.LiveData
import com.example.armovie.data.entity.list.movieList

interface MovieNetworkDataSource {

    val popularMovieList : LiveData<movieList>
    suspend fun fetchPopularMovieList()

    val nowPlayingMovieList : LiveData<movieList>
    suspend fun fetchNowPlayingMovieList()


    val upcomingMovieList : LiveData<movieList>
    suspend fun fetchUpcomingMovieList()

}