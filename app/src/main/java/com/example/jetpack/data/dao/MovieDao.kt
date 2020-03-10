package com.example.jetpack.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.jetpack.data.model.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = REPLACE)
    fun insertAll(vararg  movie: Movie)

    @Query("SELECT * FROM movie")
    fun getAll() : List<Movie>

    @Query("SELECT * FROM movie WHERE type =:type")
    fun getByType(type: String) : LiveData<List<Movie>>

    @Query("SELECT * FROM movie WHERE type =:type and favorite =:favorite")
    fun getFavorite(type: String,favorite: Boolean) : Movie

    @Query("SELECT * FROM movie WHERE favorite =:favorite")
    fun getAllFavorite(favorite: Boolean) : List<Movie>

    @Query("SELECT * FROM movie WHERE title LIKE :title")
    fun getByTitle(title: String): Movie

    @Update
    fun update(vararg movie: Movie)

    @Delete
    fun delete(movie: Movie)

}