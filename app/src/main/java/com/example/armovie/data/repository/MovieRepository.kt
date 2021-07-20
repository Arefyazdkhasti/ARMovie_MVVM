package com.example.armovie.data.repository

import androidx.lifecycle.LiveData
import com.example.armovie.data.entity.credits.MovieCredit
import com.example.armovie.data.entity.list.movieList
import com.example.armovie.data.entity.single.movieDetail

interface MovieRepository {

    suspend fun getPopularMovieList(): LiveData<movieList>

    suspend fun getNowPlayingMovieList(): LiveData<movieList>

    suspend fun getUpcomingMovieList(): LiveData<movieList>

    suspend fun getMovieDetail(movieId: Int): LiveData<movieDetail>

    suspend fun getMovieCredits(movieId: Int): LiveData<MovieCredit>
}