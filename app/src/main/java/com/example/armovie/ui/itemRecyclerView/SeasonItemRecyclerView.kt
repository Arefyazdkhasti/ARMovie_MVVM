package com.example.armovie.ui.itemRecyclerView

import com.example.armovie.R
import com.example.armovie.data.entity.TvShow.Season
import com.example.armovie.data.entity.credits.Cast
import com.example.armovie.data.network.BASE_IMAGE_MOVIE
import com.example.armovie.utility.GlideApp
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.season_item.view.*

class SeasonItemRecyclerView (private val season: Season): Item<GroupieViewHolder>() {

    override fun getLayout(): Int = R.layout.season_item
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        viewHolder.itemView.season_poster.clipToOutline = true

        GlideApp.with(viewHolder.itemView)
            .load(BASE_IMAGE_MOVIE + season.posterPath)
            .placeholder(R.drawable.load)
            .into(viewHolder.itemView.season_poster)

        viewHolder.itemView.season_name.text = season.name

        viewHolder.itemView.season_overview.text = season.overview

        viewHolder.itemView.season_episode_count.text = season.episodeCount.toString()
    }


}