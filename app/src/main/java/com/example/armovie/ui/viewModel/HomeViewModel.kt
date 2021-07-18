package com.example.armovie.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.armovie.data.repository.MovieRepository
import com.example.armovie.utility.lazyDeferred

class HomeViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    val movieListEntry by lazyDeferred{
            movieRepository.getMostSpecificMovieList("popular")
    }
}