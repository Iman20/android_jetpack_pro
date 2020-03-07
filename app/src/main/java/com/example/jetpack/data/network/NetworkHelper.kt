package com.example.jetpack.data.network

import android.content.Context
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.ANRequest
import com.example.jetpack.utils.Constant
import okhttp3.OkHttpClient

class NetworkHelper constructor(context: Context) {

    init {
        initFastAndroidNetworking(context)
    }

    private fun initFastAndroidNetworking(applicationContext: Context?) {
        val okHttpClient = OkHttpClient().newBuilder()
            .build()
        AndroidNetworking.initialize(applicationContext, okHttpClient)
    }

    fun connect(path : String) : ANRequest<ANRequest<*>>?{
        return AndroidNetworking.get(path).build()
    }

}