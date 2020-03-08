package com.example.jetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.jetpack.data.model.Movie
import com.example.jetpack.repository.TvRepository
import com.example.jetpack.vo.Resource

class TVViewModel(
    private val tvRepository: TvRepository
) : ViewModel() {

    fun getTv() : LiveData<Resource<List<Movie>>>{
        return tvRepository.getTv()
    }
}