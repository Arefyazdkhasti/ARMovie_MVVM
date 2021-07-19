package com.example.armovie.ui.adpter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.armovie.R
import com.example.armovie.data.entity.list.movieItem
import com.example.armovie.data.network.BASE_IMAGE_MOVIE
import com.example.armovie.utility.GlideApp
import kotlinx.android.synthetic.main.movie_item.view.*


class RecyclerItemMovieAdapter(
    private val context: Context,
    private val data: List<movieItem>,
    //private val type: TypeGetProduct
) : RecyclerView.Adapter<RecyclerItemMovieAdapter.ItemMovieViewHolder>() {

    companion object {
        const val KEY_ID = "id"
        const val KEY_NAME = "name"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMovieViewHolder =
        ItemMovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.movie_item,
                    parent, false
                )
        )

    override fun onBindViewHolder(holder: ItemMovieViewHolder, position: Int) {
        holder.setData(data[position])
    }

    override fun getItemCount() = data.size


    inner class ItemMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
         {

        //private val rootView = itemView.movie_image
        private val image = itemView.movie_image
        private val title = itemView.movie_name

        fun setData(movieItem: movieItem) {

            GlideApp.with(context)
                .load(BASE_IMAGE_MOVIE + movieItem.posterPath)
                .into(image)

            title.text = movieItem.title

            /*rootView.setOnClickListener {
                context.startActivity<DetailActivity>(
                    KEY_ID to data.id,
                    KEY_NAME to data.title
                )
            }*/


        }
    }

}