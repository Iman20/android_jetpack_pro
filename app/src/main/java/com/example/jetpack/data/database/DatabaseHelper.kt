package com.example.jetpack.data.database

import android.content.Context

class DatabaseHelper constructor(context: Context) {

    val movieDatabase : MovieDatabase = MovieDatabase.getInstance(context)

}