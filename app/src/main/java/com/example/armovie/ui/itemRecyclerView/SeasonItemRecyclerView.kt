package com.example.armovie.ui.itemRecyclerView

import com.example.armovie.R
import com.example.armovie.data.entity.TvShow.Season
import com.example.armovie.data.entity.credits.Cast
import com.example.armovie.data.network.BASE_IMAGE_MOVIE
import com.example.armovie.databinding.SeasonItemBinding
import com.example.armovie.databinding.VideoItemBinding
import com.example.armovie.utility.GlideApp
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class SeasonItemRecyclerView(private val season: Season) : Item<GroupieViewHolder>() {

    override fun getLayout(): Int = R.layout.season_item
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        val binding = SeasonItemBinding.bind(viewHolder.itemView)

        binding.seasonPoster.clipToOutline = true

        GlideApp.with(viewHolder.itemView)
            .load(BASE_IMAGE_MOVIE + season.posterPath)
            .placeholder(R.drawable.load)
            .into(binding.seasonPoster)

        binding.seasonName.text = season.name

        binding.seasonOverview.text = season.overview

        binding.seasonEpisodeCount.text = season.episodeCount.toString()
    }


}