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
import com.example.jetpack.viewmodel.TVViewModel
import com.example.jetpack.vo.Status
import org.koin.android.viewmodel.ext.android.viewModel
import kotlinx.android.synthetic.main.tv_layout.*

class TvFragment : Fragment() {
    private lateinit var adapter: MovieAdapter
    private var tvs = arrayListOf<Movie>()
    private val tvViewModel : TVViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initData()
        initListener()
        return inflater.inflate(R.layout.tv_layout, container, false)
    }


    private fun showDialog(b: Boolean) {
        if(b){
            progressBar.show()
        } else {
            progressBar.hide()
        }
    }

    private fun initListener() {
        tvViewModel.getTv().observe(this, Observer {
            res ->
            if (res.status == Status.SUCCESS){
                res.data?.let { tvs.addAll(it) }
                initRecycle()
                showDialog(false)
            }

        })
    }

    private fun initData() {
        tvViewModel.getTv()
    }

    private fun initRecycle() {
        tvRv.layoutManager = LinearLayoutManager(context)
        adapter = context?.let { MovieAdapter(tvs) }!!
        tvRv.adapter = adapter
        adapter.setOnClickListener(object : MovieAdapter.OnItemClickListener{
            override fun OnItemClicked(movie: Movie) {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.DATA, movie)
                startActivity(intent)
            }
        })
    }
}