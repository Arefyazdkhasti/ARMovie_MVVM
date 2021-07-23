package com.example.armovie.data.network.response

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.armovie.data.entity.TvShow.TvShowDetail
import com.example.armovie.data.entity.TvShowList.TvShowList
import com.example.armovie.data.entity.credits.MovieCredit
import com.example.armovie.data.entity.movieList.movieList
import com.example.armovie.data.entity.search.SearchMovie
import com.example.armovie.data.entity.movie.movieDetail
import com.example.armovie.data.network.TMDBApiService
import com.example.armovie.utility.NoConnectivityException

class MovieNetworkDataSourceImpl(
    private val tmdbApiService: TMDBApiService
) : MovieNetworkDataSource {

    private val _popularMovieList = MutableLiveData<movieList>()
    override val popularMovieList: LiveData<movieList>
        get() = _popularMovieList

    private val _nowPlayingMovieList = MutableLiveData<movieList>()
    override val nowPlayingMovieList: LiveData<movieList>
        get() = _nowPlayingMovieList

    private val _upcomingMovieList = MutableLiveData<movieList>()
    override val upcomingMovieList: LiveData<movieList>
        get() = _upcomingMovieList

    private val _movieDetail = MutableLiveData<movieDetail>()
    override val movieDetail: LiveData<movieDetail>
        get() = _movieDetail

    private val _movieCredits = MutableLiveData<MovieCredit>()
    override val movieCredits: LiveData<MovieCredit>
        get() = _movieCredits

    private val _searchMovie = MutableLiveData<SearchMovie>()
    override val searchMovie: LiveData<SearchMovie>
        get() = _searchMovie


    private val _tvShows = MutableLiveData<TvShowList>()
    override val tvShowsList: LiveData<TvShowList>
        get() = _tvShows

    private val _tvShowDetail = MutableLiveData<TvShowDetail>()
    override val tvShowDetail: LiveData<TvShowDetail>
        get() = _tvShowDetail

    override suspend fun fetchPopularMovieList() {
        try {
            val fetchMovie = tmdbApiService.getPopularMovieAsync(1).await()
            _popularMovieList.postValue(fetchMovie)

        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)

        }
    }

    override suspend fun fetchNowPlayingMovieList() {
        try {
            val fetchMovie = tmdbApiService.getNowPlayingMovieAsync(1).await()
            _nowPlayingMovieList.postValue(fetchMovie)

        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)

        }
    }

    override suspend fun fetchUpcomingMovieList() {
        try {
            val fetchMovie = tmdbApiService.getUpcomingMovieAsync(1).await()
            _upcomingMovieList.postValue(fetchMovie)

        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)

        }
    }

    override suspend fun fetchMovieDetail(movieId: Int) {
        try {
            val fetchMovieDetail = tmdbApiService.getMovieDetailAsync(movieId).await()
            _movieDetail.postValue(fetchMovieDetail)

        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)

        }
    }

    override suspend fun fetchMovieCredits(movieId: Int) {
        try {
            val fetchMovieCredits = tmdbApiService.getMovieCreditsAsync(movieId).await()
            _movieCredits.postValue(fetchMovieCredits)

        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)

        }
    }

    override suspend fun fetchSearchedMovies(query: String) {
        try {
            val fetchSearchedMovies = tmdbApiService.searchMovieAsync(query).await()
            _searchMovie.postValue(fetchSearchedMovies)

        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)
        }
    }

    override suspend fun fetchTvShowList() {
        try {
            val fetchTvShowList = tmdbApiService.getTvShowsAsync(1).await()
            _tvShows.postValue(fetchTvShowList)

        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)
        }
    }

    override suspend fun fetchTvShowDetail(tvShowId:Int) {
        try {
            val fetchTvShowDetail = tmdbApiService.getTvShowsDetailsAsync(tvShowId).await()
            _tvShowDetail.postValue(fetchTvShowDetail)

        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)
        }

    }
}