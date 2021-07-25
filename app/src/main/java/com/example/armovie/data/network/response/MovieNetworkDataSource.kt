package com.example.armovie.data.network.response

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

interface MovieNetworkDataSource {

    //movies
    val popularMovieList: LiveData<movieList>
    suspend fun fetchPopularMovieList()

    val nowPlayingMovieList: LiveData<movieList>
    suspend fun fetchNowPlayingMovieList()


    val upcomingMovieList: LiveData<movieList>
    suspend fun fetchUpcomingMovieList()


    val movieDetail: LiveData<movieDetail>
    suspend fun fetchMovieDetail(movieId: Int)

    val movieCredits: LiveData<MovieCredit>
    suspend fun fetchMovieCredits(movieId: Int)

    val searchMovie: LiveData<SearchMovieResponse>
    suspend fun fetchSearchedMovies(query: String, include_adult: Boolean)

    //TV Shows
    val tvShowsList: LiveData<TvShowList>
    suspend fun fetchTvShowList()

    val tvShowDetail: LiveData<TvShowDetail>
    suspend fun fetchTvShowDetail(tvShowId: Int)

    val searchTvShow: LiveData<SearchTvShowResponse>
    suspend fun fetchSearchedTvShows(query: String, include_adult: Boolean)

    //Person
    val personDetail: LiveData<PersonDetail>
    suspend fun fetchPersonDetail(personID:Int)

    val personCombinedCredit : LiveData<CombinedCredit>
    suspend fun fetchPersonCombinedCredit(personID: Int)

    //Videos
    val movieVideos : LiveData<VideoList>
    suspend fun fetchMovieVideos(movieId: Int)
}