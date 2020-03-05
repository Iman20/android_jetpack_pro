package com.example.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.jetpack.data.model.Movie
import com.example.jetpack.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.detail_activity.*

class DetailActivity : AppCompatActivity() {
    companion object {
        const val DATA = "bundle_data"
    }

    private lateinit var detailViewModel: DetailViewModel
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initData()
        initView()
        initListener()

    }

    private fun initListener() {
        detailViewModel.getDetail().observe(this, Observer {
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

    private fun initData() {
        detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)
    }

    private fun initView() {
        val data = intent.getParcelableExtra(DATA) as Movie
        textTitle.text = data.title
        textDescription.text = data.description
        textDirector.text = data.voteAvarage
        textWriter.text = data.voteCount
        textScreen.text = data.popularity
        Glide.with(this).load(data.posterPath).into(imageView)
        backButton.setOnClickListener {
            onBackPressed()
        }
        favorite.setOnClickListener {
            if (isFavorite){
                detailViewModel.deleteFavorite(this, data)
                favorite.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite))

            } else {
                favorite.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_fill))
                detailViewModel.addFavorite(this, data)
            }

        }
        detailViewModel.initialDetail(this, data.title!!)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(itemId: Int) {
        when(itemId){
            android.R.id.home -> {
                onBackPressed()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.action, menu)
        return super.onCreateOptionsMenu(menu)
    }

}
