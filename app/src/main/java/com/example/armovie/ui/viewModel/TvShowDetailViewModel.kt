package com.example.armovie.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.armovie.data.repository.MovieRepository
import com.example.armovie.utility.lazyDeferred

class TvShowDetailViewModel(
    private val tvShowID: Int,
    private val movieRepository: MovieRepository
) : ViewModel() {


    val tvShowDetail by lazyDeferred {
        movieRepository.getTvShowDetail(tvShowID)
    }

}