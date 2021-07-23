package com.example.armovie.ui.itemRecyclerView

import com.example.armovie.R
import com.example.armovie.data.entity.movieList.movieItem
import com.example.armovie.data.network.BASE_IMAGE_MOVIE
import com.example.armovie.utility.GlideApp
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieItemRecyclerView(private val movieItem: movieItem): Item<GroupieViewHolder>() {


    override fun getLayout(): Int = R.layout.movie_item
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        GlideApp.with(viewHolder.itemView)
            .load(BASE_IMAGE_MOVIE + movieItem.posterPath)
            .placeholder(R.drawable.load)
            .into(viewHolder.itemView.movie_image)


        viewHolder.itemView.movie_image.clipToOutline = true
        viewHolder.itemView.movie_name.text = movieItem.title

    }



}