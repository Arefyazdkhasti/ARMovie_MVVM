package com.example.armovie.data.repository

import androidx.lifecycle.LiveData
import com.example.armovie.data.entity.list.movieList
import com.example.armovie.data.network.response.MovieNetworkDataSource
import kotlinx.coroutines.*

class MovieRepositoryImpl(
    private val movieNetworkDataSource: MovieNetworkDataSource
): MovieRepository{


    override suspend fun getMostSpecificMovieList(type: String): LiveData<movieList> {
        //return withContext(Dispatchers.IO) {
            fetchMovie()
            return movieNetworkDataSource.movieList
        //}
    }


    private suspend fun fetchMovie() {
        movieNetworkDataSource.fetchMovieList("popular")
    }
}