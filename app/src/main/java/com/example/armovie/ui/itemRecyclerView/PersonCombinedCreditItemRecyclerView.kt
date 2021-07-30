package com.example.armovie.ui.itemRecyclerView

import com.example.armovie.R
import com.example.armovie.data.entity.Person.Cast
import com.example.armovie.data.entity.movieList.movieItem
import com.example.armovie.data.network.BASE_IMAGE_MOVIE
import com.example.armovie.databinding.CombinedCreditItemBinding
import com.example.armovie.databinding.VideoItemBinding
import com.example.armovie.utility.GlideApp
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class PersonCombinedCreditItemRecyclerView(val cast: Cast) : Item<GroupieViewHolder>() {


    override fun getLayout(): Int = R.layout.combined_credit_item
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        val binding = CombinedCreditItemBinding.bind(viewHolder.itemView)

        GlideApp.with(viewHolder.itemView)
            .load(BASE_IMAGE_MOVIE + cast.posterPath)
            .placeholder(R.drawable.load)
            .into(binding.combinedCreditImage)


        binding.combinedCreditImage.clipToOutline = true
        binding.combinedCreditName.text = cast.title
    }


}