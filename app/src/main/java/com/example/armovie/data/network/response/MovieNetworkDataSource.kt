package com.example.armovie.data.network.response

import androidx.lifecycle.LiveData
import com.example.armovie.data.entity.credits.MovieCredit
import com.example.armovie.data.entity.list.movieList
import com.example.armovie.data.entity.single.movieDetail

interface MovieNetworkDataSource {

    val popularMovieList : LiveData<movieList>
    suspend fun fetchPopularMovieList()

    val nowPlayingMovieList : LiveData<movieList>
    suspend fun fetchNowPlayingMovieList()


    val upcomingMovieList : LiveData<movieList>
    suspend fun fetchUpcomingMovieList()


    val movieDetail : LiveData<movieDetail>
    suspend fun fetchMovieDetail(movieId:Int)

    val movieCredits : LiveData<MovieCredit>
    suspend fun fetchMovieCredits(movieId:Int)

}