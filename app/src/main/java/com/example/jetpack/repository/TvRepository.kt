package com.example.jetpack.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.ANRequest
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.jetpack.BuildConfig
import com.example.jetpack.dao.MovieDao
import com.example.jetpack.model.Movie
import com.example.jetpack.utils.RateLimiter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class TvRepository(
    private val movieDao: MovieDao,
    private val fan: ANRequest<ANRequest<*>>?
) {
    companion object{
        val repoListRateLimit = RateLimiter<String>(1, TimeUnit.MINUTES)
    }
    fun initialTv():LiveData<List<Movie>>{
        val listMovie = MutableLiveData<List<Movie>>()

        refreshMovie()

        GlobalScope.launch {
            Log.d("APP ", "Post Fetch")
            listMovie.postValue(movieDao.getAll())

        }



        return listMovie


    }

    private fun refreshMovie(){
        val shouldFetch = repoListRateLimit.shouldFetch("tvMovie")
        Log.d("APP ", "Refresh")
        if (shouldFetch){
            Log.d("APP ", "Masuk Fetch")
            val listItems = ArrayList<Movie>()
            val url = "https://api.themoviedb.org/3/discover/tv?api_key=${BuildConfig.API_KEY}&language=en-US"
            var posterPath = "https://image.tmdb.org/t/p/w185"

            test(AndroidNetworking.get(url)
                .build())

            fan?.getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {
                        val list = response?.getJSONArray("results")

                        for (i in 0 until list!!.length()){
                            val movie = list!!.getJSONObject(i)
                            val movieItems = Movie()
                            movieItems.title = movie.getString("name")
                            movieItems.description = movie.getString("overview")
                            movieItems.posterPath = posterPath +  movie.getString("poster_path")
                            movieItems.backdropPath = posterPath +  movie.getString("backdrop_path")
                            movieItems.popularity = movie.getString("popularity")
                            movieItems.voteCount = movie.getString("vote_count")
                            movieItems.voteAvarage = movie.getString("vote_average")
                            listItems.add(movieItems)
                            GlobalScope.launch {
                                movieDao.insertAll(movieItems)
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