package com.example.armovie.data.repository

import androidx.lifecycle.LiveData
import com.example.armovie.data.entity.list.movieList

interface MovieRepository {

    suspend fun getPopularMovieList(): LiveData<movieList>

    suspend fun getNowPlayingMovieList(): LiveData<movieList>

    suspend fun getUpcomingMovieList(): LiveData<movieList>
}