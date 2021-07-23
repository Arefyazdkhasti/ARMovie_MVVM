package com.example.armovie.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.armovie.databinding.MovieFragmentBinding
import com.example.armovie.ui.base.ScopedFragment
import com.example.armovie.ui.viewModel.MovieViewModel
import com.example.armovie.ui.viewModel.MovieViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class MovieFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: MovieViewModelFactory by instance()

    private lateinit var viewModel: MovieViewModel

    private var _binding: MovieFragmentBinding? = null
    private val binding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MovieFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(MovieViewModel::class.java)

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}