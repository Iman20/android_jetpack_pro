package com.example.jetpack.utils

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class AppExecutors {
    private lateinit var diskIO: Executor
    private lateinit var networkIO: Executor
    private lateinit var mainThread: Executor

    init {
        diskIO = Executors.newSingleThreadExecutor()
        networkIO = Executors.newFixedThreadPool(3)
        mainThread = MainThreadExecutor()
    }


    fun diskIO(): Executor {
        return diskIO
    }

    fun networkIO(): Executor {
        return networkIO
    }

    fun mainThread(): Executor {
        return mainThread
    }

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}
