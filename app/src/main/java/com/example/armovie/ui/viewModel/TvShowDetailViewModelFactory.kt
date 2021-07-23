package com.example.armovie.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.armovie.data.repository.MovieRepository

class TvShowDetailViewModelFactory(
    private val tvShowID: Int,
    private val movieRepository: MovieRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TvShowDetailViewModel(
            tvShowID,
            movieRepository
        ) as T
    }
}