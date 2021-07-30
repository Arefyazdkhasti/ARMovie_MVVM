package com.example.armovie.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.cooltechworks.views.shimmer.ShimmerRecyclerView
import com.example.armovie.R
import com.example.armovie.data.entity.movieList.movieItem
import com.example.armovie.ui.fragment.HomeFragmentDirections
import com.example.armovie.ui.itemRecyclerView.MovieItemRecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder


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

        //TODO see all page
        /*seeAll.setOnClickListener{
            context.startActivity<ArchiveActivity>(

            )
        }*/

        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(data.toMovieItems())
        }

        movieRecycleView.apply {
            adapter = groupAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        groupAdapter.setOnItemClickListener { item, view ->
            (item as? MovieItemRecyclerView)?.let {
                val actionDetailHome = HomeFragmentDirections.sendMovieId(it.movieItem.id)
                Navigation.findNavController(view).navigate(actionDetailHome)
            }
        }

    }

    fun hideShimmer() {
        movieRecycleView.hideShimmerAdapter()
    }

    fun showShimmer() {
        movieRecycleView.showShimmerAdapter()
    }


}