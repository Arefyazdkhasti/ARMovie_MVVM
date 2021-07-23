package com.example.armovie.data.repository

import androidx.lifecycle.LiveData
import com.example.armovie.data.entity.TvShow.TvShowDetail
import com.example.armovie.data.entity.TvShowList.TvShowList
import com.example.armovie.data.entity.credits.MovieCredit
import com.example.armovie.data.entity.movieList.movieList
import com.example.armovie.data.entity.search.SearchMovie
import com.example.armovie.data.entity.movie.movieDetail
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

    override suspend fun getSearchMovies(query: String): LiveData<SearchMovie> {
        fetchSearchMovie(query)
        return movieNetworkDataSource.searchMovie
    }

    override suspend fun getTvShows(): LiveData<TvShowList> {
        fetchTvShows()
        return movieNetworkDataSource.tvShowsList
    }

    override suspend fun getTvShowDetail(tvShowId: Int): LiveData<TvShowDetail> {
        fetchTvShowDetail(tvShowId)
        return movieNetworkDataSource.tvShowDetail
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

    private suspend fun fetchSearchMovie(query: String){
        movieNetworkDataSource.fetchSearchedMovies(query)
    }

    private suspend fun fetchTvShows(){
        movieNetworkDataSource.fetchTvShowList()
    }
    private suspend fun fetchTvShowDetail(tvShowId: Int){
        movieNetworkDataSource.fetchTvShowDetail(tvShowId)
    }
}