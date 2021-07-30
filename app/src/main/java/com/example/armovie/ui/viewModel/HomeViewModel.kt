package com.example.armovie.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.armovie.data.repository.MovieRepository
import com.example.armovie.utility.lazyDeferred

class HomeViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    val popularMovieListEntry by lazyDeferred{
            movieRepository.getPopularMovieList()
    }

    val nowPlayingMovieListEntry by lazyDeferred{
        movieRepository.getNowPlayingMovieList()
    }

    val upcomingMovieListEntry by lazyDeferred{
        movieRepository.getUpcomingMovieList()
    }
}