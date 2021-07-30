package com.example.armovie.data.network.response

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.armovie.data.entity.Person.CombinedCredit
import com.example.armovie.data.entity.Person.PersonDetail
import com.example.armovie.data.entity.TvShow.TvShowDetail
import com.example.armovie.data.entity.TvShowList.TvShowList
import com.example.armovie.data.entity.Video.VideoList
import com.example.armovie.data.entity.credits.MovieCredit
import com.example.armovie.data.entity.movieList.movieList
import com.example.armovie.data.entity.search.movie.SearchMovieResponse
import com.example.armovie.data.entity.movie.movieDetail
import com.example.armovie.data.entity.search.tvShow.SearchTvShowResponse
import com.example.armovie.data.entity.trending.TrendingList
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

    private val _searchMovie = MutableLiveData<SearchMovieResponse>()
    override val searchMovie: LiveData<SearchMovieResponse>
        get() = _searchMovie


    private val _tvShows = MutableLiveData<TvShowList>()
    override val tvShowsList: LiveData<TvShowList>
        get() = _tvShows

    private val _tvShowDetail = MutableLiveData<TvShowDetail>()
    override val tvShowDetail: LiveData<TvShowDetail>
        get() = _tvShowDetail

    private val _searchTvShow = MutableLiveData<SearchTvShowResponse>()
    override val searchTvShow: LiveData<SearchTvShowResponse>
        get() = _searchTvShow

    private val _personDetail = MutableLiveData<PersonDetail>()
    override val personDetail: LiveData<PersonDetail>
        get() = _personDetail

    private val _personCombinedCredit = MutableLiveData<CombinedCredit>()
    override val personCombinedCredit: LiveData<CombinedCredit>
        get() = _personCombinedCredit

    private val _movieVideos = MutableLiveData<VideoList>()
    override val movieVideos: LiveData<VideoList>
        get() = _movieVideos

    private val _trendingList = MutableLiveData<TrendingList>()
    override val trendingList: LiveData<TrendingList>
        get() = _trendingList

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

    override suspend fun fetchSearchedMovies(query: String, include_adult: Boolean) {
        try {
            val fetchSearchedMovies = tmdbApiService.searchMovieAsync(query, include_adult).await()
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

    override suspend fun fetchTvShowDetail(tvShowId: Int) {
        try {
            val fetchTvShowDetail = tmdbApiService.getTvShowsDetailsAsync(tvShowId).await()
            _tvShowDetail.postValue(fetchTvShowDetail)

        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)
        }

    }

    override suspend fun fetchSearchedTvShows(query: String, include_adult: Boolean) {
        try {
            val fetchSearchTvShowResult =
                tmdbApiService.searchTvShowAsync(query, include_adult).await()
            _searchTvShow.postValue(fetchSearchTvShowResult)

        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)
        }
    }

    override suspend fun fetchPersonDetail(personID: Int) {
        try {
            val fetchPersonDetail = tmdbApiService.getPersonDetailAsync(personID).await()
            _personDetail.postValue(fetchPersonDetail)

        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)
        }
    }

    override suspend fun fetchPersonCombinedCredit(personID: Int) {
        try {
            val fetchCombinedCredit = tmdbApiService.getPersonCombinedCreditAsync(personID).await()
            _personCombinedCredit.postValue(fetchCombinedCredit)

        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)
        }
    }

    override suspend fun fetchMovieVideos(movieId: Int) {
        try {
            val fetchMovieVideo = tmdbApiService.getMovieVideoListAsync(movieId).await()
            _movieVideos.postValue(fetchMovieVideo)

        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)
        }
    }

    override suspend fun fetchTrendingList() {
        try {
            val fetchTrendingList = tmdbApiService.getTrendingListAsync().await()
            _trendingList.postValue(fetchTrendingList)

        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)
        }
    }
}