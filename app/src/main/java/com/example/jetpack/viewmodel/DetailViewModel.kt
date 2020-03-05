package com.example.jetpack.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpack.model.Movie

class DetailViewModel : ViewModel() {

    private var movie = MutableLiveData<Movie>()

    fun initialDetail(context: Context, title: String){
//        val db = MovieDatabase(context)
//        GlobalScope.launch {
//            val movies = db.MovieDao().getByTitle(title)
//            movie.postValue(movies)
//        }
    }

    fun addFavorite(context: Context, movie: Movie){
//        val db = MovieDatabase(context)
//        GlobalScope.launch {
//            db.MovieDao().insertAll(movie)
//        }
    }

    fun deleteFavorite(context: Context, movie: Movie){
//        val db = MovieDatabase(context)
//        GlobalScope.launch {
//            db.MovieDao().delete(movie)
//        }
    }
    internal fun getDetail(): LiveData<Movie>{
        return movie
    }

}