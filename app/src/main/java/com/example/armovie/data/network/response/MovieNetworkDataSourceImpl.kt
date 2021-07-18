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

    private val _movieList = MutableLiveData<movieList>()

    override val movieList: LiveData<movieList>
        get() = _movieList

    override suspend fun fetchMovieList(type: String) {
        try {

            val fetchMovie = tmdbApiService.getPopularMovie(1).await()
            _movieList.postValue(fetchMovie)

        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)
        }
    }
}