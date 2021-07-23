package com.example.armovie.data.network.response

import androidx.lifecycle.LiveData
import com.example.armovie.data.entity.TvShow.TvShowDetail
import com.example.armovie.data.entity.TvShowList.TvShowList
import com.example.armovie.data.entity.credits.MovieCredit
import com.example.armovie.data.entity.movieList.movieList
import com.example.armovie.data.entity.search.SearchMovie
import com.example.armovie.data.entity.movie.movieDetail

interface MovieNetworkDataSource {

    //movies
    val popularMovieList : LiveData<movieList>
    suspend fun fetchPopularMovieList()

    val nowPlayingMovieList : LiveData<movieList>
    suspend fun fetchNowPlayingMovieList()


    val upcomingMovieList : LiveData<movieList>
    suspend fun fetchUpcomingMovieList()


    val movieDetail : LiveData<movieDetail>
    suspend fun fetchMovieDetail(movieId:Int)

    val movieCredits : LiveData<MovieCredit>
    suspend fun fetchMovieCredits(movieId:Int)

    val searchMovie : LiveData<SearchMovie>
    suspend fun fetchSearchedMovies(query:String)

    //TV Shows
    val tvShowsList: LiveData<TvShowList>
    suspend fun fetchTvShowList()

    val tvShowDetail: LiveData<TvShowDetail>
    suspend fun fetchTvShowDetail(tvShowId:Int)


}