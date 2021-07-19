package com.example.armovie.data.repository

import androidx.lifecycle.LiveData
import com.example.armovie.data.entity.list.movieList
import com.example.armovie.data.network.response.MovieNetworkDataSource

class MovieRepositoryImpl(
    private val movieNetworkDataSource: MovieNetworkDataSource
): MovieRepository{


    override suspend fun getPopularMovieList(): LiveData<movieList> {
            fetchPopularMovie()
            return movieNetworkDataSource.popularMovieList
    }

    override suspend fun getNowPlayingMovieList(): LiveData<movieList> {
        fetchNowPlayingMovie()
        return movieNetworkDataSource.nowPlayingMovieList
    }

    override suspend fun getUpcomingMovieList(): LiveData<movieList> {
        fetchUpcomingMovie()
        return movieNetworkDataSource.upcomingMovieList
    }


    private suspend fun fetchPopularMovie() {
        movieNetworkDataSource.fetchPopularMovieList()
    }

    private suspend fun fetchNowPlayingMovie() {
        movieNetworkDataSource.fetchNowPlayingMovieList()
    }

    private suspend fun fetchUpcomingMovie() {
        movieNetworkDataSource.fetchUpcomingMovieList()
    }
}