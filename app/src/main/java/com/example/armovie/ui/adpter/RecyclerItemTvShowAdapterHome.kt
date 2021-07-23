package com.example.armovie.ui.adpter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.armovie.R
import com.example.armovie.data.entity.TvShow.TvShowDetail
import com.example.armovie.data.entity.TvShowList.TvShow
import com.example.armovie.data.entity.movieList.movieItem
import com.example.armovie.data.network.BASE_IMAGE_MOVIE
import com.example.armovie.ui.fragment.HomeFragmentDirections
import com.example.armovie.ui.fragment.TVShowsFragmentDirections
import com.example.armovie.utility.GlideApp
import kotlinx.android.synthetic.main.movie_item.view.*
import kotlinx.android.synthetic.main.tv_show_item.view.*


class RecyclerItemTvShowAdapterHome(
    private val context: Context,
    private val data: List<TvShow>,
    //private val type: TypeGetProduct
) : RecyclerView.Adapter<RecyclerItemTvShowAdapterHome.ItemTvShowViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTvShowViewHolder =
        ItemTvShowViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.tv_show_item,
                    parent, false
                )
        )

    override fun onBindViewHolder(holder: ItemTvShowViewHolder, position: Int) {
        holder.setData(data[position],position)
    }

    override fun getItemCount() = data.size


    inner class ItemTvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
         {

        private val rootView = itemView.tv_show_image
        private val image = itemView.tv_show_image
        private val title = itemView.tv_show_name
        private val overView = itemView.tv_show_overview

        fun setData(item: TvShow,position: Int) {

            image.clipToOutline = true

            GlideApp.with(context)
                .load(BASE_IMAGE_MOVIE + item.posterPath)
                .fitCenter()
                .into(image)

            title.text = item.name

            overView.text = item.overview

            rootView.setOnClickListener {
                val actionDetail = TVShowsFragmentDirections.sendTvShowId(data[position].id)
                Navigation.findNavController(it).navigate(actionDetail)
            }

        }
    }

}