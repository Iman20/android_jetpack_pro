package com.example.jetpack.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpack.DetailActivity
import com.example.jetpack.R
import com.example.jetpack.data.adapter.MovieAdapter
import com.example.jetpack.data.model.Movie
import com.example.jetpack.viewmodel.FavoriteViewModel
import kotlinx.android.synthetic.main.favorite_layout.*

class FavoriteFragment : Fragment() {

    private lateinit var adapter: MovieAdapter
    private var fav = arrayListOf<Movie>()
    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initData()
        initListener()
        return inflater.inflate(
            R.layout.favorite_layout, container, false)
    }

    override fun onResume() {
        fav.clear()
        favoriteViewModel.initialFavorite(context!!)
        super.onResume()
    }

    private fun initData() {
        favoriteViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FavoriteViewModel::class.java)
        favoriteViewModel.initialFavorite(context!!)
    }

    private fun initListener() {
        favoriteViewModel.getFavorite().observe(this, Observer {
                favorite ->
            if (favorite != null){
                fav.addAll(favorite)
                initRecycle()
                showDialog(false)
            }

        })
    }

    private fun initRecycle() {
        favoriteRv.layoutManager = LinearLayoutManager(context)
        adapter = context?.let { MovieAdapter(fav) }!!
        favoriteRv.adapter = adapter
        adapter.setOnClickListener(object : MovieAdapter.OnItemClickListener{
            override fun OnItemClicked(movie: Movie) {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.DATA, movie)
                startActivity(intent)
            }
        })
    }

    private fun showDialog(b: Boolean) {
        if(b){
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}