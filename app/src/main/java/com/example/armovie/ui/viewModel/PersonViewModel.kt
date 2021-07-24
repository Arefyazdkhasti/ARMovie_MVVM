package com.example.armovie.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.armovie.data.repository.MovieRepository
import com.example.armovie.utility.lazyDeferred

class PersonViewModel(
    private val personID: Int,
    private val movieRepository: MovieRepository
) : ViewModel() {

    val personDetail by lazyDeferred {
        movieRepository.getPersonDetail(personID)
    }

    val personCombinedCredit by lazyDeferred {
        movieRepository.getPersonCombinedCredit(personID)
    }

}