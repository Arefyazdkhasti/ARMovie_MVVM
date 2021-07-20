package com.example.armovie.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cooltechworks.views.shimmer.ShimmerRecyclerView
import com.example.armovie.R
import com.example.armovie.data.entity.list.movieItem
import com.example.armovie.data.entity.list.movieList
import com.example.armovie.ui.adpter.RecyclerItemMovieAdapter
import com.example.armovie.ui.fragment.HomeFragmentDirections
import com.example.armovie.ui.itemRecyclerView.MovieItemRecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.home_fragment.view.*


class MovieView(
    contextInstant: Context,
    attrs: AttributeSet
) : FrameLayout(contextInstant, attrs) {

    private val listTitle: AppCompatTextView
    private val seeAll: AppCompatTextView
    private val movieRecycleView: ShimmerRecyclerView

    companion object {
        const val TITLE_KEY = "title"
        const val TYPE_KEY = "type"
    }

    init {
        val view = inflate(context, R.layout.movie_view, this)

        //get attrs
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MovieView)
        val title = typedArray.getString(R.styleable.MovieView_title)
        //recycle type array after use
        typedArray.recycle()


        listTitle = view.findViewById(R.id.movie_type)
        seeAll = view.findViewById(R.id.movie_type_see_all)
        movieRecycleView = view.findViewById(R.id.movie_recyclerView)

        listTitle.text = title

        movieRecycleView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)


    }

    private fun List<movieItem>.toMovieItems(): List<MovieItemRecyclerView> = this.map {
        MovieItemRecyclerView(it)
    }

    fun initRecycler(data: List<movieItem>, type: String) {

        movieRecycleView.showShimmerAdapter()
        /*seeAll.setOnClickListener{
            context.startActivity<ArchiveActivity>(
                TITLE_KEY to listTitle.text.toString(),
                TYPE_KEY to type
            )
        }*/

        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(data.toMovieItems())
        }

        val itemAdapter = RecyclerItemMovieAdapter(context,data)

        movieRecycleView.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        //TODO cannot cast item to movieItem
        /*groupAdapter.setOnItemClickListener { item, view ->
            item as movieItem
        }*/

    }

    fun hideShimmer() {
        movieRecycleView.hideShimmerAdapter()
    }

    fun showShimmer() {
        movieRecycleView.showShimmerAdapter()
    }


}