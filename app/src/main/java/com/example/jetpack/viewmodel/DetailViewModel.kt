package com.example.jetpack.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpack.data.model.Movie
import com.example.jetpack.repository.DetailRepository
import com.example.jetpack.vo.Resource

class DetailViewModel(
    private val detailRepository: DetailRepository
) : ViewModel() {


    fun initFavorite(title: String): LiveData<Movie>{
        return detailRepository.initFavorite(title)
    }


    fun initialDetail(context: Context, title: String){
//        val db = MovieDatabase(context)
//        GlobalScope.launch {
//            val movies = db.MovieDao().getByTitle(title)
//            movie.postValue(movies)
//        }
    }

    fun addFavorite(movie: Movie){
        detailRepository.addFavorite(movie)
    }

    fun deleteFavorite(movie: Movie){
        detailRepository.deleteFavorite(movie)
    }

}