package com.example.armovie.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.armovie.data.repository.MovieRepository
import com.example.armovie.ui.insterface.SearchQuery
import com.example.armovie.utility.lazyDeferred

class MovieViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {


    val searchMovieResult by lazyDeferred {
        movieRepository.getSearchMovies("black widow")
    }
}
