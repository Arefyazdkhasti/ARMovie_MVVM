package com.example.armovie.ui.itemRecyclerView

import com.example.armovie.R
import com.example.armovie.data.entity.credits.Cast
import com.example.armovie.data.network.BASE_IMAGE_MOVIE
import com.example.armovie.databinding.CastItemBinding
import com.example.armovie.databinding.VideoItemBinding
import com.example.armovie.utility.GlideApp
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class CastItemRecyclerView (val cast: Cast): Item<GroupieViewHolder>() {

    override fun getLayout(): Int = R.layout.cast_item
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        val binding = CastItemBinding.bind(viewHolder.itemView)

        binding.castImage.clipToOutline = true
        GlideApp.with(viewHolder.itemView)
            .load(BASE_IMAGE_MOVIE + cast.profilePath)
            .placeholder(R.drawable.load)
            .into(binding.castImage)

        binding.castName.text = cast.name

        binding.castCharacter.text = cast.character
    }


}