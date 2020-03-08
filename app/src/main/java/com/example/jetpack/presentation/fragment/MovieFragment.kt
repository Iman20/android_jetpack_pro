package com.example.jetpack.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpack.DetailActivity
import com.example.jetpack.R
import com.example.jetpack.data.adapter.MovieAdapter
import com.example.jetpack.data.model.Movie
import com.example.jetpack.utils.hide
import com.example.jetpack.utils.show
import com.example.jetpack.viewmodel.MovieViewModel
import com.example.jetpack.vo.Status
import kotlinx.android.synthetic.main.movie_layout.*
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {
    private lateinit var adapter: MovieAdapter
    private var movies = arrayListOf<Movie>()
    private val movieModel : MovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initData()
        initListener()
        return inflater.inflate(R.layout.movie_layout, container, false)
    }

    private fun showLoading(b: Boolean) {
        if(b){
            progressBar.show()
        } else {
            progressBar.hide()
        }

    }

    private fun initListener() {
        movieModel.getMovie().observe(this, Observer {
            res ->
            if (res.status == Status.SUCCESS){
                res.data?.let { movies.addAll(it) }
                initRecycle()
                showLoading(false)
            }
        })
    }


    private fun initData() {
        movieModel.getMovie()
    }

    private fun initRecycle() {
        movieRv.layoutManager = LinearLayoutManager(context)
        adapter = context?.let { MovieAdapter(movies) }!!
        movieRv.adapter = adapter
        adapter.setOnClickListener(object : MovieAdapter.OnItemClickListener{
            override fun OnItemClicked(movie: Movie) {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.DATA, movie)
                startActivity(intent)
            }
        })
    }
}