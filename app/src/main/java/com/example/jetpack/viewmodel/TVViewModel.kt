package com.example.jetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.jetpack.model.Movie
import com.example.jetpack.repository.TvRepository

class TVViewModel(
    private val tvRepository: TvRepository
) : ViewModel() {

    fun getTv() : LiveData<List<Movie>>{
        return tvRepository.initialTv()
    }
}