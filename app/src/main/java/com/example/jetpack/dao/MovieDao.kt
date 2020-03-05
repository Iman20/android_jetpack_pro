package com.example.jetpack.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.jetpack.model.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = REPLACE)
    fun insertAll(vararg  movie: Movie)

    @Query("SELECT * FROM movie")
    fun getAll() : List<Movie>

    @Query("SELECT * FROM movie WHERE title LIKE :title")
    fun getByTitle(title: String): Movie

    @Update
    fun update(vararg movie: Movie)

    @Delete
    fun delete(movie: Movie)

}