package com.example.armovie.data.network.response

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.armovie.data.entity.list.movieList
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
}