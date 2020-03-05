package com.example.jetpack

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.androidnetworking.AndroidNetworking
import com.example.jetpack.di.allModule
import com.example.jetpack.fragment.FavoriteFragment
import com.example.jetpack.fragment.MovieFragment
import com.example.jetpack.fragment.TvFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initFastAndroidNetworking(applicationContext)
        AndroidNetworking.initialize(applicationContext)
        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(allModule)
        }
    }

    private fun initFastAndroidNetworking(applicationContext: Context?) {
        val okHttpClient = OkHttpClient().newBuilder()
            .build()
        AndroidNetworking.initialize(applicationContext, okHttpClient)

    }

    private fun initView() {
        bottomNav.setOnNavigationItemSelectedListener(navigateListener)
        val movieFragment = MovieFragment()
        addFragment(movieFragment)

    }


    private val navigateListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.movie -> {
                val fragment = MovieFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.tv -> {
                val fragment = TvFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.favorite -> {
                val fragment = FavoriteFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content, fragment, fragment.javaClass.simpleName)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.action, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_change_settings) {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
        return super.onOptionsItemSelected(item)
    }

}
