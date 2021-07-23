package com.example.armovie.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.armovie.R
import com.example.armovie.data.entity.TvShow.Season
import com.example.armovie.data.entity.movie.Genre
import com.example.armovie.data.network.BASE_IMAGE_MOVIE
import com.example.armovie.databinding.MovieDetailFragmentBinding
import com.example.armovie.databinding.TvShowDetailFragmentBinding
import com.example.armovie.databinding.TvShowsFragmentBinding
import com.example.armovie.ui.base.ScopedFragment
import com.example.armovie.ui.itemRecyclerView.GenreItemRecyclerView
import com.example.armovie.ui.itemRecyclerView.SeasonItemRecyclerView
import com.example.armovie.ui.viewModel.MovieDetailViewModel
import com.example.armovie.ui.viewModel.MovieDetailViewModelFactory
import com.example.armovie.ui.viewModel.TvShowDetailViewModel
import com.example.armovie.ui.viewModel.TvShowDetailViewModelFactory
import com.example.armovie.utility.BlurTransformation
import com.example.armovie.utility.GlideApp
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.factory

class TvShowDetailFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactoryInstanceFactory: ((Int) -> TvShowDetailViewModelFactory) by factory()

    private lateinit var viewModel: TvShowDetailViewModel

    private var _binding: TvShowDetailFragmentBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TvShowDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val safeArgs = arguments?.let { TvShowDetailFragmentArgs.fromBundle(it) }
        val tvShowID = safeArgs?.tvShowId
        if (tvShowID != null) {
            viewModel = ViewModelProvider(
                this,
                viewModelFactoryInstanceFactory(tvShowID)
            ).get(TvShowDetailViewModel::class.java)
        }
        bindUI()
    }

    private fun bindUI() = launch {
        val tvShow = viewModel.tvShowDetail.await()

        //curve image corners
        binding.posterImage.clipToOutline = true

        tvShow.observe(viewLifecycleOwner, Observer { tvShowDetail ->
            if (tvShowDetail == null) return@Observer

            //toolbar
            (activity as? AppCompatActivity)?.supportActionBar?.title = tvShowDetail.name
            //(activity as? AppCompatActivity)?.supportActionBar?.subtitle = movie_detail.releaseDate

            //backdrop image
            GlideApp.with(requireContext())
                .load(BASE_IMAGE_MOVIE + tvShowDetail.backdropPath)
                .transform(BlurTransformation(requireContext()))
                .placeholder(R.drawable.load)
                .into(binding.backdropImage)

            //poster image
            GlideApp.with(requireContext())
                .load(BASE_IMAGE_MOVIE + tvShowDetail.posterPath)
                .placeholder(R.drawable.load)
                .into(binding.posterImage)

            //Genre RecyclerView
            initGenreRecyclerView(
                tvShowDetail.genres.toGenreItems(),
                binding.genreRecyclerView
            )

            //Seasons RecyclerView
            initSeasonRecyclerView(
                tvShowDetail.seasons.toSeasonItems(),
                binding.seasonsRecyclerView
            )

            //number of episodes
            binding.numberOfEpisodes.text = tvShowDetail.numberOfEpisodes.toString()

            //number of seasons
            binding.numberOfSeaons.text = tvShowDetail.numberOfSeasons.toString()

            //rating
            binding.voteAverage.text = tvShowDetail.voteAverage.toString()

            // episode runtime
            //binding.runtime.text = movie_detail.runtime.toString() + "min"

            //overview
            binding.overview.text = tvShowDetail.overview

            //tag line
            binding.tagline.text = tvShowDetail.tagline

            //spoken language
            binding.originalLanguage.text = tvShowDetail.originalLanguage
        })
    }

    private fun initGenreRecyclerView(
        items: List<GenreItemRecyclerView>,
        recyclerView: RecyclerView
    ) {
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(items)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = groupAdapter
        }
    }


    private fun initSeasonRecyclerView(
        items: List<SeasonItemRecyclerView>,
        recyclerView: RecyclerView
    ) {
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(items)
        }

        recyclerView.apply {
            layoutManager =
                LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = groupAdapter
        }
    }


    private fun List<Genre>.toGenreItems(): List<GenreItemRecyclerView> = this.map {
        GenreItemRecyclerView(it)
    }

    private fun List<Season>.toSeasonItems(): List<SeasonItemRecyclerView> = this.map {
        SeasonItemRecyclerView(it)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}