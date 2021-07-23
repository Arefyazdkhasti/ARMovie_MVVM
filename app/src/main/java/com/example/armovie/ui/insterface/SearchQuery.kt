package com.example.armovie.ui.insterface

interface SearchQuery {

    companion object {
        lateinit var query: String

        fun getQueryString(): String = query

        fun setQueryString(q: String) {
            query = q
        }

        fun clearQuery() {
            query = ""
        }
    }


}