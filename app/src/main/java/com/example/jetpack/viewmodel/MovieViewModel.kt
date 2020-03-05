package com.example.jetpack.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.jetpack.BuildConfig
import com.example.jetpack.data.model.Movie
import org.json.JSONObject


class MovieViewModel : ViewModel() {

    val listMovie = MutableLiveData<ArrayList<Movie>>()

    fun initialMovie(){
        val listItems = ArrayList<Movie>()
        val url = "https://api.themoviedb.org/3/discover/movie?api_key=${BuildConfig.API_KEY}&language=en-US"
        var posterPath = "https://image.tmdb.org/t/p/w185"


        AndroidNetworking.get(url)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener{
                override fun onResponse(response: JSONObject?) {
                    Log.d("RESPONSE ", response.toString())
                    val list = response?.getJSONArray("results")

                    for (i in 0 until list!!.length()){
                        val movie = list!!.getJSONObject(i)
                        val movieItems = Movie()
                        movieItems.title = movie.getString("title")
                        movieItems.description = movie.getString("overview")
                        movieItems.posterPath = posterPath +  movie.getString("poster_path")
                        movieItems.backdropPath = posterPath +  movie.getString("backdrop_path")
                        movieItems.popularity = movie.getString("popularity")
                        movieItems.voteCount = movie.getString("vote_count")
                        movieItems.voteAvarage = movie.getString("vote_average")
                        listItems.add(movieItems)
                    }
                    listMovie.postValue(listItems)

                }

                override fun onError(anError: ANError?) {
                    Log.d("RESPONSE ", anError.toString())

                }
            })


    }

    internal fun getMoview() : LiveData<ArrayList<Movie>>{
        return  listMovie
    }
}