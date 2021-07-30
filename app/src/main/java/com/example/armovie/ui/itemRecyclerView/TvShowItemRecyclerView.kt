package com.example.armovie.ui.itemRecyclerView

import com.example.armovie.R
import com.example.armovie.data.entity.TvShowList.TvShow
import com.example.armovie.data.network.BASE_IMAGE_MOVIE
import com.example.armovie.databinding.TvShowItemBinding
import com.example.armovie.databinding.VideoItemBinding
import com.example.armovie.utility.GlideApp
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class TvShowItemRecyclerView( val tvShow: TvShow) : Item<GroupieViewHolder>() {


    override fun getLayout(): Int = R.layout.tv_show_item
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        val binding = TvShowItemBinding.bind(viewHolder.itemView)

        GlideApp.with(viewHolder.itemView)
            .load(BASE_IMAGE_MOVIE + tvShow.posterPath)
            .placeholder(R.drawable.load)
            .fitCenter()
            .into(binding.tvShowImage)


        binding.tvShowImage.clipToOutline = true
        binding.tvShowName.text = tvShow.name

        binding.tvShowOverview.text = tvShow.overview

    }
}

