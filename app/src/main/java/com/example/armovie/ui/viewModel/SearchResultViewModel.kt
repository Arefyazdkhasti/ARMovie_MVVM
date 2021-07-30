package com.example.armovie.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.armovie.data.entity.SearchQuery
import com.example.armovie.data.repository.MovieRepository
import com.example.armovie.utility.lazyDeferred

class SearchResultViewModel(
    private val searchQuery: SearchQuery,
    private val movieRepository: MovieRepository
) : ViewModel() {


    val searchMovieResult by lazyDeferred {
        movieRepository.getSearchMovies(searchQuery.query,searchQuery.include_adult)
    }

    val searchTvShowResult by lazyDeferred {
        movieRepository.getSearchTvShow(searchQuery.query,searchQuery.include_adult)
    }

}
