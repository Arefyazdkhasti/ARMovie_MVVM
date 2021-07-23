package com.example.armovie.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.armovie.data.repository.MovieRepository

class SearchViewModelFactory(
    private val movieRepository: MovieRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(
            movieRepository
        ) as T
    }
}