package com.mlbench.fitmeapp.Utils

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp
import io.paperdb.Paper

@HiltAndroidApp
class Fitme:Application() {

    override fun onCreate() {
        super.onCreate()
        Paper.init(this)
        initialize()
    }
    private fun initialize() {
        SharePref.init(this)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }


}