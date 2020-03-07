package com.example.jetpack.utils

import com.example.jetpack.BuildConfig

object Constant {
    const val baseUrl = "\"https://api.themoviedb.org/3/discover/tv?api_key=${BuildConfig.API_KEY}&language=en-US\""
    const val posterUrl = "https://image.tmdb.org/t/p/w185"
}