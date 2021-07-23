package com.example.armovie.ui.itemRecyclerView

import com.example.armovie.R
import com.example.armovie.data.entity.credits.Cast
import com.example.armovie.data.network.BASE_IMAGE_MOVIE
import com.example.armovie.utility.GlideApp
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.cast_item.view.*

class CastItemRecyclerView (private val cast: Cast): Item<GroupieViewHolder>() {

    override fun getLayout(): Int = R.layout.cast_item
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        viewHolder.itemView.cast_image.clipToOutline = true
        GlideApp.with(viewHolder.itemView)
            .load(BASE_IMAGE_MOVIE + cast.profilePath)
            .placeholder(R.drawable.load)
            .into(viewHolder.itemView.cast_image)

        viewHolder.itemView.cast_name.text = cast.name

        viewHolder.itemView.cast_character.text = cast.character
    }


}