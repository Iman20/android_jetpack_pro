package com.example.jetpack.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.jetpack.BuildConfig
import com.example.jetpack.data.model.Movie
import com.example.jetpack.repository.MovieRepository
import com.example.jetpack.vo.Resource
import org.json.JSONObject


class MovieViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    fun getMovie() : LiveData<Resource<List<Movie>>>{
        return movieRepository.getMovie()
    }
}