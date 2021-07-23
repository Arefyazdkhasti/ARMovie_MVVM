package com.example.armovie.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchQuery(
    val query :String,
    val include_adult:Boolean
):Parcelable
