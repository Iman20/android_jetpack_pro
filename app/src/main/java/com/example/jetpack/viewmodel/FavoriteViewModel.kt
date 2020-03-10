package com.example.jetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.jetpack.data.model.Movie
import com.example.jetpack.repository.FavoriteRepository

class FavoriteViewModel (
    private val favoriteRepository: FavoriteRepository
): ViewModel(){

    fun getFavorite():LiveData<List<Movie>>{
        return favoriteRepository.getListFavorite()
    }

}