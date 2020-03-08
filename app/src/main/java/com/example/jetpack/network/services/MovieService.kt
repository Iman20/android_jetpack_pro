package com.example.jetpack.network.services

import androidx.lifecycle.LiveData
import com.example.jetpack.BuildConfig
import com.example.jetpack.data.model.Movie
import com.example.jetpack.data.model.ResponseModel
import com.example.jetpack.network.ApiResponse
import retrofit2.http.GET

interface MovieService {
    @GET("tv?api_key=${BuildConfig.API_KEY}&language=en-US")
    fun getTv(): LiveData<ApiResponse<ResponseModel>>

    @GET("movie?api_key=${BuildConfig.API_KEY}&language=en-US")
    fun getMovie(): LiveData<ApiResponse<ResponseModel>>
}