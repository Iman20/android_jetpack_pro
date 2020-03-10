package com.example.jetpack.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpack.data.database.DatabaseHelper
import com.example.jetpack.data.model.Movie
import com.example.jetpack.utils.AppExecutors

class FavoriteRepository(
    private val appExecutors: AppExecutors,
    private val databaseHelper: DatabaseHelper
) {
    private val _liveData = MutableLiveData<List<Movie>>()

    fun getListFavorite() : LiveData<List<Movie>>{
        appExecutors.diskIO().execute {
            _liveData .postValue(databaseHelper.movieDatabase.movieDao().getAllFavorite(true))
        }
        return _liveData

    }
}