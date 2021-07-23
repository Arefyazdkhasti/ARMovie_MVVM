package com.example.armovie.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.armovie.data.entity.TvShow.TvShowDetail
import com.example.armovie.data.entity.TvShowList.TvShow
import com.example.armovie.databinding.TvShowsFragmentBinding
import com.example.armovie.ui.adpter.RecyclerItemTvShowAdapterHome
import com.example.armovie.ui.base.ScopedFragment
import com.example.armovie.ui.itemRecyclerView.TvShowItemRecyclerView
import com.example.armovie.ui.viewModel.TVShowsViewModel
import com.example.armovie.ui.viewModel.TVShowsViewModelFactory
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class TVShowsFragment : ScopedFragment(), KodeinAware {

    override val kodein: Kodein by closestKodein()
    private val viewModelFactory: TVShowsViewModelFactory by instance()

    private lateinit var viewModel: TVShowsViewModel

    private var _binding: TvShowsFragmentBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TvShowsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(TVShowsViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUI()
    }

    private fun bindUI() = launch {
        binding.tvShowsRecyclerView.showShimmerAdapter()

        val tvShows = viewModel.tvShowsListEntry.await()

        tvShows.observe(viewLifecycleOwner, Observer { tvShowList ->
            if (tvShowList == null) return@Observer

            binding.tvShowsRecyclerView.hideShimmerAdapter()

            initTvShowRecycler(tvShowList.results/*.toTvShowItems()*/, binding.tvShowsRecyclerView)

        })
    }

    private fun initTvShowRecycler(data: List<TvShow>, recyclerView: RecyclerView) {
        /*val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(data)
        }*/
        val groupAdapter = RecyclerItemTvShowAdapterHome(requireContext(),data)
        recyclerView.apply {
            adapter = groupAdapter
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        }
    }

    private fun List<TvShow>.toTvShowItems(): List<TvShowItemRecyclerView> = this.map {
        TvShowItemRecyclerView(it)
    }

}