package com.example.jetpack.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.jetpack.dao.MovieDao
import com.example.jetpack.model.Movie

@Database(
    entities = [Movie::class],
    version = 1
)
abstract class MovieDatabase : RoomDatabase(){
    abstract fun movieDao() : MovieDao

    companion object {
        @Volatile private var instance: MovieDatabase? = null
        fun getInstance(context: Context): MovieDatabase{
            if (instance == null){
                synchronized(MovieDatabase::class.java){
                    if (instance == null){
                        instance = Room.databaseBuilder(context, MovieDatabase::class.java, "movie.db").build()
                    }
                }
            }
            return instance!!
        }

        val MIGRATION_1_2 : Migration = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                //To change body of created functions use File | Settings | File Templates.
            }
        }
    }


}
