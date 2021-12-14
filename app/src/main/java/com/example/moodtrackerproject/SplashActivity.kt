package com.example.moodtrackerproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moodtrackerproject.routing.Routes

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Routes.goToMainActivity(this)
        finish()
    }
}
