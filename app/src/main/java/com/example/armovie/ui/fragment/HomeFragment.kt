package com.example.armovie.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.armovie.data.network.ConnectivityInterceptorImpl
import com.example.armovie.data.network.TMDBApiService
import com.example.armovie.databinding.HomeFragmentBinding
import com.example.armovie.ui.viewModel.HomeViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    private var _binding: HomeFragmentBinding? = null
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel

        val api = TMDBApiService(ConnectivityInterceptorImpl(this.requireContext()))
        GlobalScope.launch {
            val test = api.getPopularMovie(1).await()
            println("TEST ==> $test")
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}