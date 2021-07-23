package com.example.armovie.ui.itemRecyclerView

import com.example.armovie.R
import com.example.armovie.data.entity.TvShowList.TvShow
import com.example.armovie.data.network.BASE_IMAGE_MOVIE
import com.example.armovie.utility.GlideApp
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.tv_show_item.view.*

class TvShowItemRecyclerView(private val tvShow: TvShow) : Item<GroupieViewHolder>() {


    override fun getLayout(): Int = R.layout.tv_show_item
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        GlideApp.with(viewHolder.itemView)
            .load(BASE_IMAGE_MOVIE + tvShow.posterPath)
            .placeholder(R.drawable.load)
            .fitCenter()
            .into(viewHolder.itemView.tv_show_image)


        viewHolder.itemView.tv_show_image.clipToOutline = true
        viewHolder.itemView.tv_show_name.text = tvShow.name

        viewHolder.itemView.tv_show_overview.text = tvShow.overview

    }
}

