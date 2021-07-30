package com.example.armovie.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.armovie.data.entity.SearchQuery
import com.example.armovie.databinding.SearchFragmentBinding
import com.example.armovie.ui.base.ScopedFragment
import com.example.armovie.ui.viewModel.SearchViewModel
import com.example.armovie.ui.viewModel.SearchViewModelFactory
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


        binding.includeAdult.setOnCheckedChangeListener { c, b ->
            if (b) binding.includeAdultHint.visibility = View.VISIBLE
            else binding.includeAdultHint.visibility = View.GONE
        }

        binding.searchBtn.setOnClickListener {
            val query = binding.searchEditText.text
            if (query.toString() != "") {

                //make the search query
                val q = query.toString()
                val ia = binding.includeAdult.isChecked
                val searchQuery = SearchQuery(q, ia)

                //pass arg
                val actionSendQuery = SearchFragmentDirections.sendSearchQuery(searchQuery)
                Navigation.findNavController(it).navigate(actionSendQuery)

            } else {
                Toast.makeText(requireContext(), "Type the title to search", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}