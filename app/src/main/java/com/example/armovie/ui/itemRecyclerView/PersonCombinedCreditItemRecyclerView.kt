package com.example.armovie.ui.itemRecyclerView

import com.example.armovie.R
import com.example.armovie.data.entity.Person.Cast
import com.example.armovie.data.entity.movieList.movieItem
import com.example.armovie.data.network.BASE_IMAGE_MOVIE
import com.example.armovie.utility.GlideApp
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.combined_credit_item.view.*

class PersonCombinedCreditItemRecyclerView(val cast: Cast): Item<GroupieViewHolder>() {


    override fun getLayout(): Int = R.layout.combined_credit_item
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        GlideApp.with(viewHolder.itemView)
            .load(BASE_IMAGE_MOVIE + cast.posterPath)
            .placeholder(R.drawable.load)
            .into(viewHolder.itemView.combined_credit_image)


        viewHolder.itemView.combined_credit_image.clipToOutline = true
        viewHolder.itemView.combined_credit_name.text = cast.title
    }



}