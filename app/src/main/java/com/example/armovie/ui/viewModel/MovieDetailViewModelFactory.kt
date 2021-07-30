package com.example.armovie.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.armovie.data.repository.MovieRepository

class MovieDetailViewModelFactory(
    private val movieId: Int,
    private val movieRepository: MovieRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailViewModel(
            movieId,
            movieRepository
        ) as T
    }
}