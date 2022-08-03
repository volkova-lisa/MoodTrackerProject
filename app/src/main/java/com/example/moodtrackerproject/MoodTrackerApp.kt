package com.example.moodtrackerproject

import android.annotation.SuppressLint
import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.yariksoffice.lingver.Lingver
import timber.log.Timber

class MoodTrackerApp : Application() {

    override fun onCreate() {
        super.onCreate()

        firebase()
        timber()
        Lingver.init(this)
    }

    private fun firebase() {
        FirebaseApp.initializeApp(this)
        if (BuildConfig.DEBUG) {
//            Firebase.crashlytics.setCrashlyticsCollectionEnabled(false)
        } else {
            Firebase.crashlytics.setCrashlyticsCollectionEnabled(true)
        }
    }

    private fun timber() {
        Timber.plant(
            object : Timber.DebugTree() {
                @SuppressLint("DefaultLocale")
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return String.format(
                        "@@ %s.%s:%d thread[%s]",
                        super.createStackElementTag(element),
                        element.methodName,
                        element.lineNumber,
                        Thread.currentThread().name
                    )
                }

                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    if (BuildConfig.DEBUG) {
                        super.log(priority, tag, message, t)
                    }
                }
            }
        )
    }
}
