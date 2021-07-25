package com.example.armovie.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.armovie.R
import com.example.armovie.data.entity.Video.VideoResult
import com.example.armovie.data.entity.credits.Cast
import com.example.armovie.data.entity.movie.Genre
import com.example.armovie.data.network.BASE_IMAGE_MOVIE
import com.example.armovie.data.network.BASE_YOUTUBE_WATCH_URL
import com.example.armovie.databinding.MovieDetailFragmentBinding
import com.example.armovie.ui.base.ScopedFragment
import com.example.armovie.ui.itemRecyclerView.CastItemRecyclerView
import com.example.armovie.ui.itemRecyclerView.GenreItemRecyclerView
import com.example.armovie.ui.itemRecyclerView.VideoItemRecyclerView
import com.example.armovie.ui.viewModel.MovieDetailViewModel
import com.example.armovie.ui.viewModel.MovieDetailViewModelFactory
import com.example.armovie.utility.BlurTransformation
import com.example.armovie.utility.GlideApp
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.movie_detail_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.factory


class MovieDetailFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactoryInstanceFactory: ((Int) -> MovieDetailViewModelFactory) by factory()

    private lateinit var viewModel: MovieDetailViewModel

    private var _binding: MovieDetailFragmentBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MovieDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val safeArgs = arguments?.let { MovieDetailFragmentArgs.fromBundle(it) }
        val movieID = safeArgs?.movieID
        if (movieID != null)
            viewModel = ViewModelProvider(this, viewModelFactoryInstanceFactory(movieID)).get(
                MovieDetailViewModel::class.java
            )

        //TODO make shimmer works
        /*binding.movieDetailRoot.visibility = View.GONE
        binding.shimmerFrameLayout.visibility = View.VISIBLE
        binding.shimmerFrameLayout.startShimmerAnimation()*/
        bindUI()
    }

    @SuppressLint("SetTextI18n")
    private fun bindUI() = launch {
        val movieDetail = viewModel.movieDetail.await()

        /* binding.movieDetailRoot.visibility = View.VISIBLE
         binding.shimmerFrameLayout.stopShimmerAnimation()
         binding.shimmerFrameLayout.visibility = View.GONE*/

        //curve image corners
        binding.posterImage.clipToOutline = true


        movieDetail.observe(viewLifecycleOwner, Observer { movie_detail ->
            if (movie_detail == null) return@Observer
            //toolbar
            (activity as? AppCompatActivity)?.supportActionBar?.title = movie_detail.title
            //(activity as? AppCompatActivity)?.supportActionBar?.subtitle = movie_detail.releaseDate

            //backdrop image
            GlideApp.with(this@MovieDetailFragment)
                .load(BASE_IMAGE_MOVIE + movie_detail.backdropPath)
                .transform(BlurTransformation(requireContext()))
                .placeholder(R.drawable.load)
                .into(binding.backdropImage)

            //poster image
            GlideApp.with(this@MovieDetailFragment)
                .load(BASE_IMAGE_MOVIE + movie_detail.posterPath)
                .placeholder(R.drawable.load)
                .into(binding.posterImage)

            //Genre RecyclerView
            initGenreRecyclerView(movie_detail.genres.toGenreItems(), binding.genreRecyclerView)

            //release date
            binding.releaseDate.text = movie_detail.releaseDate

            //rating
            binding.voteAverage.text = movie_detail.voteAverage.toString()

            //runtime
            binding.runtime.text = movie_detail.runtime.toString() + "min"

            //overview
            binding.overview.text = movie_detail.overview

            //tag line
            binding.tagline.text = movie_detail.tagline

        })

        val movieCredits = viewModel.movieCredits.await()

        movieCredits.observe(viewLifecycleOwner, Observer { movie_credits ->

            //cast RecyclerView
            initCastRecyclerView(movie_credits.cast.toCastItems(), binding.castRecyclerView)
        })

        val movieVideos = viewModel.movieVideo.await()

        movieVideos.observe(viewLifecycleOwner, Observer { vidoe ->
            initVideoRecyclerView(vidoe.videoResults.toVideoItem(), binding.videoRecyclerView)
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

    private fun initCastRecyclerView(
        items: List<CastItemRecyclerView>,
        recyclerView: RecyclerView
    ) {
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(items)
        }

        recyclerView.apply {
            layoutManager = GridLayoutManager(this.context, 3, GridLayoutManager.HORIZONTAL, false)
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, view ->
            (item as? CastItemRecyclerView)?.let {
                sendPersonID(it.cast.id, view)
            }
        }
    }

    private fun initVideoRecyclerView(items: List<VideoItemRecyclerView>, recyclerView: RecyclerView) {
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(items)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = groupAdapter
        }

        //TODO make it work with functional ExoPlayer
        groupAdapter.setOnItemClickListener { item, view ->
            (item as? VideoItemRecyclerView)?.let {
                openInBrowser(BASE_YOUTUBE_WATCH_URL+it.video.key)
            }
        }

    }

    private fun List<Genre>.toGenreItems(): List<GenreItemRecyclerView> = this.map {
        GenreItemRecyclerView(it)
    }

    private fun List<Cast>.toCastItems(): List<CastItemRecyclerView> = this.map {
        CastItemRecyclerView(it)
    }

    private fun List<VideoResult>.toVideoItem(): List<VideoItemRecyclerView> = this.map {
        VideoItemRecyclerView(it,requireContext())
    }

    private fun sendPersonID(personID: Int, view: View) {
        val action = MovieDetailFragmentDirections.sendPersonID(personID)
        Navigation.findNavController(view).navigate(action)
    }

    private fun openInBrowser( url: String) {
        var uri = url;
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            uri = "http://$url"

        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        startActivity(browserIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}