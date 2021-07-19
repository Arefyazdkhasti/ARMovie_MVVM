package com.example.armovie.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.armovie.data.entity.list.movieItem

import com.example.armovie.databinding.HomeFragmentBinding
import com.example.armovie.ui.base.ScopedFragment
import com.example.armovie.ui.itemRecyclerView.MovieItemRecyclerView
import com.example.armovie.ui.viewModel.HomeViewModel
import com.example.armovie.ui.viewModel.HomeViewModelFactory
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
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

        val movieList = viewModel.movieListEntry.await()

        movieList.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer

            binding?.groupLoading?.visibility = View.GONE
            binding?.popularRecyclerView?.hideShimmer()

            initRecyclerView1(it.results/*.toMovieItems()*/)

        })
    }


    private fun initRecyclerView1(items: List<movieItem>) {
        binding?.popularRecyclerView?.initRecycler(items,"popular")

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}