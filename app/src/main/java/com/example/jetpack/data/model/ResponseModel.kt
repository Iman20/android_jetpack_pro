package com.example.jetpack.data.model

import com.google.gson.annotations.SerializedName

data class ResponseModel(
    @SerializedName("results")
    var data : List<Movie>? = null
)