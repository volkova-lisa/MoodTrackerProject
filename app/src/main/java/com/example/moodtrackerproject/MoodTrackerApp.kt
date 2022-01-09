package com.example.moodtrackerproject

import android.app.Application
import timber.log.Timber

class MoodTrackerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
