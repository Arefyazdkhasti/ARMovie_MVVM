package com.example.armovie.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.armovie.data.entity.SearchQuery
import com.example.armovie.data.entity.TvShowList.TvShow
import com.example.armovie.data.entity.movieList.movieItem
import com.example.armovie.databinding.SearchResultFragmentBinding
import com.example.armovie.ui.base.ScopedFragment
import com.example.armovie.ui.itemRecyclerView.MovieItemRecyclerView
import com.example.armovie.ui.itemRecyclerView.TvShowItemRecyclerView
import com.example.armovie.ui.viewModel.SearchResultViewModel
import com.example.armovie.ui.viewModel.SearchResultViewModelFactory
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.factory

class SearchResultFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactoryInstanceFactory: ((SearchQuery) -> SearchResultViewModelFactory) by factory()

    private lateinit var viewModel: SearchResultViewModel

    private var _binding: SearchResultFragmentBinding? = null
    private val binding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchResultFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val safeArgs = arguments?.let { SearchResultFragmentArgs.fromBundle(it) }
        val searchQuery = safeArgs?.searchQuery
        if (searchQuery != null) {
            viewModel = ViewModelProvider(
                this,
                viewModelFactoryInstanceFactory(searchQuery)
            ).get(SearchResultViewModel::class.java)
        }
        bindUI()
    }

    private fun bindUI() = launch {
        val movieResult = viewModel.searchMovieResult.await()
        val tvShowResult = viewModel.searchTvShowResult.await()

        binding.movieSearchResultRecyclerView.showShimmerAdapter()
        binding.tvShowsSearchResultRecyclerView.showShimmerAdapter()

        movieResult.observe(viewLifecycleOwner, Observer { result ->
            if (result == null) return@Observer

            binding.movieSearchResultRecyclerView.hideShimmerAdapter()

            //if no result hide recyclerView
            if (result.searchResults.isEmpty()) {
                binding.movieSearchResultRecyclerView.visibility = View.INVISIBLE
                binding.noResultMovie.visibility = View.VISIBLE
            }

            //initialize movie recycler view
            initMovieResultRecyclerView(result.searchResults.toMovieItems(), binding.movieSearchResultRecyclerView)
        })

        tvShowResult.observe(viewLifecycleOwner, Observer { result ->
            if (result == null) return@Observer

            binding.tvShowsSearchResultRecyclerView.hideShimmerAdapter()

            //if no result hide recyclerView
            if (result.results.isEmpty()) {
                binding.tvShowsSearchResultRecyclerView.visibility = View.INVISIBLE
                binding.noResultTvShow.visibility = View.VISIBLE
            }
            //initialize tv show recycler view
            initTVShowResultRecyclerView(result.results.toTvShowItems(), binding.tvShowsSearchResultRecyclerView)
        })

    }

    private fun initMovieResultRecyclerView(
        searchResults: List<MovieItemRecyclerView>,
        recyclerView: RecyclerView
    ) {
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(searchResults)
        }

        recyclerView.apply {
            adapter = groupAdapter
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        }

        groupAdapter.setOnItemClickListener { item, view ->
            (item as? MovieItemRecyclerView)?.let {
                val actionDetail = SearchResultFragmentDirections.sendMovieId(it.movieItem.id)
                Navigation.findNavController(view).navigate(actionDetail)
            }
        }
    }



    private fun initTVShowResultRecyclerView(
        searchResults: List<TvShowItemRecyclerView>,
        recyclerView: RecyclerView
    ) {

        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(searchResults)
        }
        recyclerView.apply {
            adapter = groupAdapter
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }
        groupAdapter.setOnItemClickListener { item, view ->
            (item as? TvShowItemRecyclerView)?.let {
                val actionDetail = SearchResultFragmentDirections.sendTvShowId(it.tvShow.id)
                Navigation.findNavController(view).navigate(actionDetail)
            }
        }
    }

    private fun List<movieItem>.toMovieItems(): List<MovieItemRecyclerView> = this.map {
        MovieItemRecyclerView(it)
    }

    private fun List<TvShow>.toTvShowItems(): List<TvShowItemRecyclerView> = this.map {
        TvShowItemRecyclerView(it)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}