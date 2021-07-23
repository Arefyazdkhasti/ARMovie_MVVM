package com.example.armovie.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.armovie.data.repository.MovieRepository
import com.example.armovie.utility.lazyDeferred

class SearchResultViewModel(
    private val query: String,
    private val movieRepository: MovieRepository
) : ViewModel() {


    val searchMovieResult by lazyDeferred {
        movieRepository.getSearchMovies(query)
    }

    val searchTvShowResult by lazyDeferred {
        movieRepository.getSearchTvShow(query)
    }
}
