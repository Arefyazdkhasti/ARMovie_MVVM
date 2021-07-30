package com.example.armovie.ui.itemRecyclerView

import android.content.Context
import android.view.View
import com.example.armovie.R
import com.example.armovie.data.entity.Video.VideoResult
import com.example.armovie.data.network.BASE_IMAGE_MOVIE
import com.example.armovie.data.network.BASE_YOUTUBE_IMAGE_URL
import com.example.armovie.data.network.BASE_YOUTUBE_NAME
import com.example.armovie.data.network.BASE_YOUTUBE_WATCH_URL
import com.example.armovie.databinding.VideoItemBinding
import com.example.armovie.utility.GlideApp
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item


class VideoItemRecyclerView(val video: VideoResult, private val context: Context) :
    Item<GroupieViewHolder>() {

    override fun getLayout(): Int = R.layout.video_item
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        val binding = VideoItemBinding.bind(viewHolder.itemView)

        binding.videoTitle.text = video.name

        GlideApp.with(viewHolder.itemView)
            .load(BASE_YOUTUBE_IMAGE_URL + video.key + "/hqdefault.jpg")
            .placeholder(R.drawable.load)
            .into(binding.videoImage)

        if(video.site == BASE_YOUTUBE_NAME)
            binding.youtubeIcon.visibility = View.VISIBLE
        else
            binding.youtubeIcon.visibility = View.GONE

        println(BASE_YOUTUBE_WATCH_URL + video.key)

    }
}
