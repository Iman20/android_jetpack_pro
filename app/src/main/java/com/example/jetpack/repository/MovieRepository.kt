package com.example.jetpack.repository

import androidx.lifecycle.LiveData
import com.example.jetpack.data.database.DatabaseHelper
import com.example.jetpack.data.model.Movie
import com.example.jetpack.data.model.ResponseModel
import com.example.jetpack.network.NetworkBoundResource
import com.example.jetpack.network.services.MovieService
import com.example.jetpack.utils.AppExecutors
import com.example.jetpack.utils.Constant
import com.example.jetpack.utils.RateLimiter
import com.example.jetpack.vo.Resource
import java.util.concurrent.TimeUnit

class MovieRepository(
    private val appExecutors: AppExecutors,
    private val databaseHelper: DatabaseHelper,
    private val movieService: MovieService
) {
    companion object{
        val repoListRateLimit = RateLimiter<String>(3, TimeUnit.MINUTES)
    }

    fun getMovie(): LiveData<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, ResponseModel>(appExecutors){
            override fun saveCallResult(item: ResponseModel) {
                item.data?.forEach {
                    it.type = Constant.movie
                    databaseHelper.movieDatabase.movieDao().insertAll(it)
                }
            }
            override fun shouldFetch() = repoListRateLimit.shouldFetch("movieMovie")
            override fun loadFromDb() = databaseHelper.movieDatabase.movieDao().getByType(Constant.movie)
            override fun createCall() = movieService.getMovie()

        }.asLiveData()
    }
}