package com.example.armovie.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.armovie.data.repository.MovieRepository
import com.example.armovie.utility.lazyDeferred

class TVShowsViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    val tvShowsListEntry by lazyDeferred {
        movieRepository.getTvShows()
    }
}