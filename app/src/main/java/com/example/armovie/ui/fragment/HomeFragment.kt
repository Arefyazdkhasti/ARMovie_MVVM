package com.example.armovie.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.armovie.data.entity.list.movieItem

import com.example.armovie.databinding.HomeFragmentBinding
import com.example.armovie.ui.base.ScopedFragment
import com.example.armovie.ui.viewModel.HomeViewModel
import com.example.armovie.ui.viewModel.HomeViewModelFactory
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class HomeFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: HomeViewModelFactory by instance()

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
        viewModel = ViewModelProvider(this,viewModelFactory).get(HomeViewModel::class.java)

        bindUI()
        /*val api = TMDBApiService(ConnectivityInterceptorImpl(this.requireContext()))
        GlobalScope.launch {
            val test = api.getPopularMovie(1).await()
            println("TEST ==> $test")
        }*/

    }

    private fun bindUI() = launch {
        binding?.popularRecyclerView?.showShimmer()

        val popularMovieList = viewModel.popularMovieListEntry.await()

        popularMovieList.observe(viewLifecycleOwner, Observer {popularMovieList ->
            if (popularMovieList == null) return@Observer

            //binding?.groupLoading?.visibility = View.GONE
            binding?.popularRecyclerView?.hideShimmer()

            initRecyclerViewPopular(popularMovieList.results)
        })

        val nowPlayingMovieList = viewModel.nowPlayingMovieListEntry.await()

        nowPlayingMovieList.observe(viewLifecycleOwner, Observer { nowPlayingMovieList ->
            if (nowPlayingMovieList == null) return@Observer

            //binding?.groupLoading?.visibility = View.GONE
            binding?.nowPlayingRecyclerView?.hideShimmer()

            initRecyclerViewNowPlaying(nowPlayingMovieList.results)
        })

        val upcomingMovieList = viewModel.upcomingMovieListEntry.await()

        upcomingMovieList.observe(viewLifecycleOwner, Observer { upcomingMovieList ->
            if (upcomingMovieList == null) return@Observer

            //binding?.groupLoading?.visibility = View.GONE
            binding?.upcomingRecyclerView?.hideShimmer()

            initRecyclerViewUpComing(upcomingMovieList.results)
        })
    }


    private fun initRecyclerViewPopular(items: List<movieItem>) {
        binding?.popularRecyclerView?.initRecycler(items,"popular")
    }

    private fun initRecyclerViewNowPlaying(items: List<movieItem>) {
        binding?.nowPlayingRecyclerView?.initRecycler(items,"now_playing")
    }

    private fun initRecyclerViewUpComing(items: List<movieItem>) {
        binding?.upcomingRecyclerView?.initRecycler(items,"upcoming")
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}