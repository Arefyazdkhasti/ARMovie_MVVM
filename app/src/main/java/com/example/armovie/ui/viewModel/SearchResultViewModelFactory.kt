package com.example.armovie.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.armovie.data.entity.SearchQuery
import com.example.armovie.data.repository.MovieRepository

class SearchResultViewModelFactory(
    private val searchQuery: SearchQuery,
    private val movieRepository: MovieRepository
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchResultViewModel(
            searchQuery,
            movieRepository
        ) as T
    }
}