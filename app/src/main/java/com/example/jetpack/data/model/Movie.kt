package com.example.jetpack.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "movie")
@Parcelize
data class Movie (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @SerializedName("title", alternate = ["name"])
    @ColumnInfo(name = "title")
    var title: String? = null,
    @SerializedName("image")
    @ColumnInfo(name = "image")
    var image: Int = 0,
    @SerializedName("overview")
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
    @SerializedName("vote_count")
    @ColumnInfo(name = "voteCount")
    var voteCount: String? = null,
    @SerializedName("vote_average")
    @ColumnInfo(name = "voteAvarage")
    var voteAvarage: String? = null,
    @SerializedName("popularity")
    @ColumnInfo(name = "popularity")
    var popularity: String? = null,
    @SerializedName("poster_path")
    @ColumnInfo(name = "posterPath")
    var posterPath: String? = null,
    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdropPath")
    var backdropPath: String? = null,
    @ColumnInfo(name = "type")
    var type: String? = null
) : Parcelable