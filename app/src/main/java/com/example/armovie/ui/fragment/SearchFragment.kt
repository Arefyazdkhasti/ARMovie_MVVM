package com.example.armovie.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
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


        binding.searchBtn.setOnClickListener {
            val query = binding.searchEditText.text
            if (query != null) {
                val actionDetail = SearchFragmentDirections.sendQuery(query.toString())
                Navigation.findNavController(it).navigate(actionDetail)
            }
        }
    }
}