package com.example.armovie.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.armovie.data.repository.MovieRepository
import com.example.armovie.utility.lazyDeferred

class MovieDetailViewModel(
    private val movieID: Int,
    private val movieRepository: MovieRepository
) : ViewModel() {


    val movieDetail by lazyDeferred {
        movieRepository.getMovieDetail(movieID)
    }

    val movieCredits by lazyDeferred {
        movieRepository.getMovieCredits(movieID)
    }
}