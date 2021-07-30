package com.example.armovie.data.repository

import androidx.lifecycle.LiveData
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

interface MovieRepository {

    suspend fun getPopularMovieList(): LiveData<movieList>

    suspend fun getNowPlayingMovieList(): LiveData<movieList>

    suspend fun getUpcomingMovieList(): LiveData<movieList>

    suspend fun getMovieDetail(movieId: Int): LiveData<movieDetail>

    suspend fun getMovieCredits(movieId: Int): LiveData<MovieCredit>

    suspend fun getSearchMovies(query: String,include_adult:Boolean) : LiveData<SearchMovieResponse>

    suspend fun getTvShows(): LiveData<TvShowList>

    suspend fun getTvShowDetail(tvShowId: Int): LiveData<TvShowDetail>

    suspend fun getSearchTvShow(query: String,include_adult:Boolean) : LiveData<SearchTvShowResponse>

    suspend fun getPersonDetail(personID:Int) : LiveData<PersonDetail>

    suspend fun getPersonCombinedCredit(personID: Int): LiveData<CombinedCredit>

    suspend fun getMovieVideos(movieID: Int): LiveData<VideoList>

    suspend fun getTrendingList(): LiveData<TrendingList>
}