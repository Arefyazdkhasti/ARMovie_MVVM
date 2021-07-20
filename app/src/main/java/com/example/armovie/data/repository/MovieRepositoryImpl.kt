package com.example.armovie.data.repository

import androidx.lifecycle.LiveData
import com.example.armovie.data.entity.credits.MovieCredit
import com.example.armovie.data.entity.list.movieList
import com.example.armovie.data.entity.single.movieDetail
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

    override suspend fun getMovieDetail(movieId: Int): LiveData<movieDetail> {
        fetchMovieDetails(movieId)
        return movieNetworkDataSource.movieDetail
    }

    override suspend fun getMovieCredits(movieId: Int): LiveData<MovieCredit> {
        fetchMovieCredits(movieId)
        return movieNetworkDataSource.movieCredits
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

    private suspend fun fetchMovieDetails(movieId:Int) {
        movieNetworkDataSource.fetchMovieDetail(movieId)
    }
    private suspend fun fetchMovieCredits(movieId:Int) {
        movieNetworkDataSource.fetchMovieCredits(movieId)
    }
}