package com.example.jetpack.di

import com.androidnetworking.AndroidNetworking
import com.example.jetpack.BuildConfig
import com.example.jetpack.data.database.MovieDatabase
import com.example.jetpack.repository.TvRepository
import com.example.jetpack.viewmodel.TVViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

val appModule = module {

    single { MovieDatabase.getInstance(androidContext()) }
    single { get<MovieDatabase>().movieDao() }
    single { AndroidNetworking.get("\"https://api.themoviedb.org/3/discover/tv?api_key=${BuildConfig.API_KEY}&language=en-US\"").build() }

}

val repositoryModule = module {
    factory { TvRepository(get(), get()) }
}

val viewModule = module {
    viewModel { TVViewModel(get()) }
}

val allModule = listOf(appModule, viewModule, repositoryModule)

