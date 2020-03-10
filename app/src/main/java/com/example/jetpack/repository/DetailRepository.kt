package com.example.jetpack.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpack.data.database.DatabaseHelper
import com.example.jetpack.data.model.Movie
import com.example.jetpack.utils.AppExecutors
import com.example.jetpack.vo.Resource

class DetailRepository (
    private val appExecutors: AppExecutors,
    private val databaseHelper: DatabaseHelper
){
    private val _liveData = MutableLiveData<Movie>()

    fun initFavorite(title : String): LiveData<Movie>{
        appExecutors.diskIO().execute {
            _liveData.postValue(databaseHelper.movieDatabase.movieDao().getFavorite(title, true))
        }
        return _liveData
    }

    fun addFavorite(movie: Movie){
        appExecutors.diskIO().execute {
            databaseHelper.movieDatabase.movieDao().insertAll(movie)
        }
    }

    fun deleteFavorite(movie: Movie){
        appExecutors.diskIO().execute {
            databaseHelper.movieDatabase.movieDao().delete(movie)
        }
    }


}