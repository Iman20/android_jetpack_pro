package com.example.jetpack.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpack.data.model.Movie

class FavoriteViewModel : ViewModel() {

    var listFavorite  = MutableLiveData<List<Movie>>()

    fun initialFavorite(context: Context){
//        val db = MovieDatabase(context)
//        GlobalScope.launch {
//            val movies = db.MovieDao().getAll()
//            Log.d("MOVIE CATALOG ", movies.size.toString())
//            listFavorite.postValue(movies)
//        }
    }

    internal fun getFavorite(): LiveData<List<Movie>>{
        return listFavorite
    }
}