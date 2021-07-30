package com.example.armovie.ui.itemRecyclerView

import com.example.armovie.R
import com.example.armovie.data.entity.movieList.movieItem
import com.example.armovie.data.network.BASE_IMAGE_MOVIE
import com.example.armovie.databinding.MovieItemBinding
import com.example.armovie.databinding.VideoItemBinding
import com.example.armovie.utility.GlideApp
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class MovieItemRecyclerView(val movieItem: movieItem): Item<GroupieViewHolder>() {


    override fun getLayout(): Int = R.layout.movie_item
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        val binding = MovieItemBinding.bind(viewHolder.itemView)

        GlideApp.with(viewHolder.itemView)
            .load(BASE_IMAGE_MOVIE + movieItem.posterPath)
            .placeholder(R.drawable.load)
            .into(binding.movieImage)


        binding.movieImage.clipToOutline = true
        binding.movieName.text = movieItem.title

    }



}