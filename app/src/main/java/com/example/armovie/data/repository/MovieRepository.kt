package com.example.armovie.data.repository

import androidx.lifecycle.LiveData
import com.example.armovie.data.entity.list.movieList

interface MovieRepository {

    suspend fun getMostSpecificMovieList(type:String): LiveData<movieList>
}