package com.example.armovie

import android.app.Application
import com.example.armovie.data.network.ConnectivityInterceptor
import com.example.armovie.data.network.ConnectivityInterceptorImpl
import com.example.armovie.data.network.TMDBApiService
import com.example.armovie.data.network.response.MovieNetworkDataSource
import com.example.armovie.data.network.response.MovieNetworkDataSourceImpl
import com.example.armovie.data.repository.MovieRepository
import com.example.armovie.data.repository.MovieRepositoryImpl
import com.example.armovie.ui.viewModel.*
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.*

class MovieApplication: Application(),KodeinAware{
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MovieApplication))

        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { TMDBApiService(instance()) }
        bind<MovieNetworkDataSource>() with singleton { MovieNetworkDataSourceImpl(instance()) }
        bind<MovieRepository>() with singleton { MovieRepositoryImpl(instance())}
        bind() from provider { HomeViewModelFactory(instance()) }
        bind() from provider { TVShowsViewModelFactory(instance()) }
        bind() from provider { SearchViewModelFactory() }
        bind() from factory() { movieId:Int -> MovieDetailViewModelFactory(movieId,instance()) }
        bind() from factory() { tvShowId:Int -> TvShowDetailViewModelFactory(tvShowId,instance()) }
        bind() from factory() { query: String -> SearchResultViewModelFactory(query,instance()) }

    }
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}