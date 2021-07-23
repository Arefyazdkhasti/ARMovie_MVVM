package com.example.armovie.data.repository

import androidx.lifecycle.LiveData
import com.example.armovie.data.entity.TvShow.TvShowDetail
import com.example.armovie.data.entity.TvShowList.TvShowList
import com.example.armovie.data.entity.credits.MovieCredit
import com.example.armovie.data.entity.movieList.movieList
import com.example.armovie.data.entity.search.SearchMovie
import com.example.armovie.data.entity.movie.movieDetail

interface MovieRepository {

    suspend fun getPopularMovieList(): LiveData<movieList>

    suspend fun getNowPlayingMovieList(): LiveData<movieList>

    suspend fun getUpcomingMovieList(): LiveData<movieList>

    suspend fun getMovieDetail(movieId: Int): LiveData<movieDetail>

    suspend fun getMovieCredits(movieId: Int): LiveData<MovieCredit>

    suspend fun getSearchMovies(query: String) : LiveData<SearchMovie>

    suspend fun getTvShows(): LiveData<TvShowList>

    suspend fun getTvShowDetail(tvShowId: Int): LiveData<TvShowDetail>
}