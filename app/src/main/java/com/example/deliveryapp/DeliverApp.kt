package com.example.deliveryapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class DeliverApp : Application() {

    companion object {
        lateinit var application: DeliverApp
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}