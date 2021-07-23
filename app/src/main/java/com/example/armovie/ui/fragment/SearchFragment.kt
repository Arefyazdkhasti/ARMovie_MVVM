package com.example.armovie.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.armovie.data.entity.movieList.movieItem
import com.example.armovie.databinding.SearchFragmentBinding
import com.example.armovie.ui.adpter.RecyclerItemMovieAdapterMovie
import com.example.armovie.ui.base.ScopedFragment
import com.example.armovie.ui.itemRecyclerView.MovieItemRecyclerView
import com.example.armovie.ui.viewModel.SearchViewModel
import com.example.armovie.ui.viewModel.SearchViewModelFactory
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class SearchFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: SearchViewModelFactory by instance()

    private lateinit var viewModel: SearchViewModel

    private var _binding: SearchFragmentBinding? = null
    private val binding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(SearchViewModel::class.java)

        //TODO init search result recycler View
    }

    /*  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
          super.onViewCreated(view, savedInstanceState)

          binding.searchView.setOnQueryTextFocusChangeListener(object :
              SearchView.OnQueryTextListener,
              View.OnFocusChangeListener {
              override fun onQueryTextSubmit(query: String?): Boolean {

                  Log.i("TEST", "query: $query")
                  //initMovieResultRecyclerView(query)
                  //initTVShowResultRecyclerView(query)
                  return false
              }

              override fun onQueryTextChange(query: String?): Boolean {
                  Log.i("TEST", "query: $query")
                  //initMovieResultRecyclerView(query)
                  //initTVShowResultRecyclerView(query)
                  return false
              }

              override fun onFocusChange(p0: View?, p1: Boolean) {

              }
          })
    }*/

    private fun initMovieResultRecyclerView(query: String?) = launch {
        /*if (query != null) {
            //TODO find better eay to pass query to viewModel
            //viewModel.setQuery(query)
        }*/

        val movieResult = viewModel.searchMovieResult.await()

        movieResult.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer

            initMovieRecycler(it.searchResults, binding.movieSearchResultRecyclerView)

        })
    }

    private fun initMovieRecycler(
        searchResults: List<movieItem>,
        movieSearchResultRecyclerView: RecyclerView
    ) {

        /*val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(searchResults)
        }*/
        val groupAdapter = RecyclerItemMovieAdapterMovie(requireContext(), searchResults)
        movieSearchResultRecyclerView.apply {
            adapter = groupAdapter
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun List<movieItem>.toMovieItems(): List<MovieItemRecyclerView> = this.map {
        MovieItemRecyclerView(it)
    }

    private fun initTVShowResultRecyclerView(query: String?) {

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}