package com.example.armovie.ui.itemRecyclerView

import com.example.armovie.R
import com.example.armovie.data.entity.movie.Genre
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.genre_item.view.*

class GenreItemRecyclerView (private val genre: Genre): Item<GroupieViewHolder>() {

    override fun getLayout(): Int = R.layout.genre_item
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.genre_title.text = genre.name
    }


}