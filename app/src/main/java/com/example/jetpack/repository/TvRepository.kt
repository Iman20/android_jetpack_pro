package com.example.jetpack.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.ANRequest
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.jetpack.BuildConfig
import com.example.jetpack.data.dao.MovieDao
import com.example.jetpack.data.database.DatabaseHelper
import com.example.jetpack.data.model.Movie
import com.example.jetpack.data.network.NetworkHelper
import com.example.jetpack.utils.Constant
import com.example.jetpack.utils.RateLimiter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class TvRepository(
    private val databaseHelper: DatabaseHelper,
    private val networkHelper: NetworkHelper
) {
    companion object{
        val repoListRateLimit = RateLimiter<String>(1, TimeUnit.MINUTES)
    }
    fun initialTv():LiveData<List<Movie>>{
        val listMovie = MutableLiveData<List<Movie>>()
        refreshMovie()
        GlobalScope.launch {
            listMovie.postValue(databaseHelper.movieDatabase.movieDao().getAll())
        }
        return listMovie
    }

    private fun refreshMovie(){
        val shouldFetch = repoListRateLimit.shouldFetch("tvMovie")
        if (shouldFetch){
            val listItems = ArrayList<Movie>()
            var posterPath = "https://image.tmdb.org/t/p/w185"


            networkHelper.connect(Constant.baseUrl)?.getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    val list = response?.getJSONArray("results")

                    for (i in 0 until list!!.length()){
                        val movie = list!!.getJSONObject(i)
                        val movieItems = Movie()
                        movieItems.title = movie.getString("name")
                        movieItems.description = movie.getString("overview")
                        movieItems.posterPath = Constant.posterUrl +  movie.getString("poster_path")
                        movieItems.backdropPath = Constant.posterUrl +  movie.getString("backdrop_path")
                        movieItems.popularity = movie.getString("popularity")
                        movieItems.voteCount = movie.getString("vote_count")
                        movieItems.voteAvarage = movie.getString("vote_average")
                        listItems.add(movieItems)
                        GlobalScope.launch {
                            databaseHelper.movieDatabase.movieDao().insertAll(movieItems)
                        }

                    }

                }

                override fun onError(anError: ANError?) {
                    Log.d("RESPONSE ", anError.toString())

                }
            })

        }

    }

    private fun test(build: ANRequest<ANRequest<*>>?) {

    }
}