package com.example.armovie.ui.adpter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.armovie.R
import com.example.armovie.data.entity.movieList.movieItem
import com.example.armovie.data.network.BASE_IMAGE_MOVIE
import com.example.armovie.ui.fragment.SearchResultFragmentDirections
import com.example.armovie.utility.GlideApp
import kotlinx.android.synthetic.main.movie_item.view.*


class RecyclerItemMovieAdapterMovie(
    private val context: Context,
    private val data: List<movieItem>
) : RecyclerView.Adapter<RecyclerItemMovieAdapterMovie.ItemMovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMovieViewHolder =
        ItemMovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.movie_item,
                    parent, false
                )
        )

    override fun onBindViewHolder(holder: ItemMovieViewHolder, position: Int) {
        holder.setData(data[position], position)
    }

    override fun getItemCount() = data.size


    inner class ItemMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val rootView = itemView.movie_image
        private val image = itemView.movie_image
        private val title = itemView.movie_name

        fun setData(movieItem: movieItem, position: Int) {

            image.clipToOutline = true
            GlideApp.with(context)
                .load(BASE_IMAGE_MOVIE + movieItem.posterPath)
                .into(image)

            title.text = movieItem.title


            rootView.setOnClickListener {
                val actionDetailHome = SearchResultFragmentDirections.sendMovieId(data[position].id)
                Navigation.findNavController(it).navigate(actionDetailHome)
            }

        }
    }

}