package com.example.armovie.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.armovie.data.repository.MovieRepository
import androidx.lifecycle.ViewModelProvider

class HomeViewModelFactory(
    private val movieRepository: MovieRepository
):ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(
            movieRepository,
        ) as T
    }
}