package com.example.jetpack.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "movie")
@Parcelize
data class Movie (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "title")
    var title: String? = null,
    @ColumnInfo(name = "image")
    var image: Int = 0,
    @ColumnInfo(name = "description")
    var description: String? = null,
    @ColumnInfo(name = "director")
    var director: String? = null,
    @ColumnInfo(name = "writer")
    var writer: String? = null,
    @ColumnInfo(name = "screenPlay")
    var screenPlay: String? = null,
    @ColumnInfo(name = "rate")
    var rate: String? = null,
    @ColumnInfo(name = "releaseDate")
    var releaseDate: String? = null,
    @ColumnInfo(name = "voteCount")
    var voteCount: String? = null,
    @ColumnInfo(name = "voteAvarage")
    var voteAvarage: String? = null,
    @ColumnInfo(name = "popularity")
    var popularity: String? = null,
    @ColumnInfo(name = "posterPath")
    var posterPath: String? = null,
    @ColumnInfo(name = "backdropPath")
    var backdropPath: String? = null
) : Parcelable