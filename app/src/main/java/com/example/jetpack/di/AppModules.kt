package com.example.jetpack.di

import NetworkInterceptor
import com.example.jetpack.data.database.DatabaseHelper
import com.example.jetpack.network.services.MovieService
import com.example.jetpack.repository.MovieRepository
import com.example.jetpack.repository.TvRepository
import com.example.jetpack.utils.AppExecutors
import com.example.jetpack.utils.Constant
import com.example.jetpack.utils.LiveDataCallAdapterFactory
import com.example.jetpack.viewmodel.MovieViewModel
import com.example.jetpack.viewmodel.TVViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single { DatabaseHelper(androidContext()) }
    single { AppExecutors() }
    factory { NetworkInterceptor() }
    factory { provideOkHttpClient() }
    factory { provideMovieService(get()) }
    factory { provideRetrofit(get()) }

}

val repositoryModule = module {
    factory { TvRepository(get(), get(), get()) }
    factory { MovieRepository(get(), get(), get()) }
}

val viewModule = module {
    viewModel { TVViewModel(get()) }
    viewModel { MovieViewModel(get()) }
}

val allModule = listOf(appModule, viewModule, repositoryModule)


fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(Constant.baseUrl).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(LiveDataCallAdapterFactory()).build()
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder().build()
}

fun provideMovieService(retrofit: Retrofit): MovieService = retrofit.create(MovieService::class.java)


