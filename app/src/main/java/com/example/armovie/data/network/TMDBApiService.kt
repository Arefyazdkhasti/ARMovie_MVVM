package com.example.armovie.data.network

import com.example.armovie.data.entity.TvShow.TvShowDetail
import com.example.armovie.data.entity.TvShowList.TvShowList
import com.example.armovie.data.entity.credits.MovieCredit
import com.example.armovie.data.entity.movieList.movieList
import com.example.armovie.data.entity.search.movie.SearchMovieResponse
import com.example.armovie.data.entity.movie.movieDetail
import com.example.armovie.data.entity.search.tvShow.SearchTvShowResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_VERSION = 3
const val API_KEY = "cfbf3d30a57bf3a59b1fd9c68e829f1d"
const val BASE_URL_MOVIE = "https://api.themoviedb.org/"
const val BASE_IMAGE_MOVIE = "https://image.tmdb.org/t/p/w185/"

interface TMDBApiService {


    //https://api.themoviedb.org/3/movie/popular?page=1&api_key=cfbf3d30a57bf3a59b1fd9c68e829f1d
    @GET("/$API_VERSION/movie/popular")
    fun getPopularMovieAsync(
        @Query("page") page: Int
    ): Deferred<movieList>

    @GET("/$API_VERSION/movie/now_playing")
    fun getNowPlayingMovieAsync(
        @Query("page") page: Int
    ): Deferred<movieList>

    @GET("/$API_VERSION/movie/upcoming")
    fun getUpcomingMovieAsync(
        @Query("page") page: Int
    ): Deferred<movieList>


    //https://api.themoviedb.org/3/movie/497698?api_key=cfbf3d30a57bf3a59b1fd9c68e829f1d
    @GET("/$API_VERSION/movie/{id}")
    fun getMovieDetailAsync(
        @Path("id") id:Int
    ): Deferred<movieDetail>


    //https://api.themoviedb.org/3/movie/497698/credits?api_key=cfbf3d30a57bf3a59b1fd9c68e829f1d&language=en-US
    @GET("/$API_VERSION/movie/{id}/credits")
    fun getMovieCreditsAsync(
        @Path("id") id:Int
    ): Deferred<MovieCredit>

    //https://api.themoviedb.org/3/search/movie?api_key=cfbf3d30a57bf3a59b1fd9c68e829f1d&query=fear%20street
    @GET("/$API_VERSION/search/movie")
    fun searchMovieAsync(
        @Query("query") query:String,
        @Query("include_adult") include_adult:Boolean
    ): Deferred<SearchMovieResponse>



    //https://api.themoviedb.org/3/tv/popular/?page=1&api_key=cfbf3d30a57bf3a59b1fd9c68e829f1d
    @GET("/$API_VERSION/discover/tv")
    fun getTvShowsAsync(
        @Query("page") page: Int
    ): Deferred<TvShowList>

    //https://api.themoviedb.org/3/tv/60735?api_key=cfbf3d30a57bf3a59b1fd9c68e829f1d
    @GET("/$API_VERSION/tv/{id}")
    fun getTvShowsDetailsAsync(
        @Path("id") id: Int
    ): Deferred<TvShowDetail>

    //https://api.themoviedb.org/3/search/tv?api_key=cfbf3d30a57bf3a59b1fd9c68e829f1d&query=loki
    @GET("/$API_VERSION/search/tv")
    fun searchTvShowAsync(
        @Query("query") query:String,
        @Query("include_adult") include_adult:Boolean
    ): Deferred<SearchTvShowResponse>


    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): TMDBApiService {

            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL_MOVIE)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TMDBApiService::class.java)
        }
    }
}