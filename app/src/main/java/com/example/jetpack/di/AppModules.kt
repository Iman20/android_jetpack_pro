package com.example.jetpack.di

import com.androidnetworking.AndroidNetworking
import com.example.jetpack.data.database.DatabaseHelper
import com.example.jetpack.data.database.MovieDatabase
import com.example.jetpack.data.network.NetworkHelper
import com.example.jetpack.repository.TvRepository
import com.example.jetpack.utils.Constant
import com.example.jetpack.utils.RateLimiter
import com.example.jetpack.viewmodel.TVViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel
import java.util.concurrent.TimeUnit

val appModule = module {

    single { DatabaseHelper(androidContext()) }
    single { NetworkHelper(androidContext()) }

}

val repositoryModule = module {
    factory { TvRepository(get(), get()) }
}

val viewModule = module {
    viewModel { TVViewModel(get()) }
}

val allModule = listOf(appModule, viewModule, repositoryModule)

