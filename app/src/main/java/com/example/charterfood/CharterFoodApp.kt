package com.example.charterfood

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CharterFoodApp:Application(){
    init {
        instance = this
    }

    companion object {
        private var instance: CharterFoodApp? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        applicationContext()
    }
}