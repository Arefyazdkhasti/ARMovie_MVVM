package com.example.armovie.ui.itemRecyclerView

import com.example.armovie.R
import com.example.armovie.data.entity.movie.Genre
import com.example.armovie.databinding.GenreItemBinding
import com.example.armovie.databinding.VideoItemBinding
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class GenreItemRecyclerView (private val genre: Genre): Item<GroupieViewHolder>() {

    override fun getLayout(): Int = R.layout.genre_item
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        val binding = GenreItemBinding.bind(viewHolder.itemView)

       binding.genreTitle.text = genre.name
    }


}