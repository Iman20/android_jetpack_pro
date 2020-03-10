package com.example.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.jetpack.data.model.Movie
import com.example.jetpack.databinding.DetailActivityBinding
import com.example.jetpack.utils.Constant
import com.example.jetpack.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.detail_activity.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    companion object {
        const val DATA = "bundle_data"
    }

    private val detailViewModel : DetailViewModel by viewModel()
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        initView()

    }


    private fun initView() {
        val data = intent.getParcelableExtra(DATA) as Movie
        val binding : DetailActivityBinding = DataBindingUtil.setContentView(this, R.layout.detail_activity)
        binding.view = this
        binding.movie = data
        binding.posterUrl = Constant.posterUrl

        detailViewModel.initFavorite(data.title!!).observe(this, Observer {
                movie ->
            if (movie != null){
                favorite.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_fill))
                isFavorite = true
            } else {
                favorite.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite))
                isFavorite = false
            }
        })
    }


    fun onBack(){
        onBackPressed()
    }

    fun onFavorite(movie: Movie){
        if (isFavorite){
            movie.favorite = !isFavorite
            detailViewModel.deleteFavorite(movie)
            favorite.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite))

        } else {
            movie.favorite = isFavorite
            favorite.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_fill))
            detailViewModel.addFavorite(movie)
        }
    }



}
