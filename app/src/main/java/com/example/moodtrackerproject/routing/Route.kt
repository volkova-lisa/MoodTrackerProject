package com.example.moodtrackerproject.routing

import android.content.Context
import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.ui.LoginFragment

object Route {
    fun goToLogin(fragmentActivity: FragmentActivity) {
        val transaction = fragmentActivity.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, LoginFragment())
        transaction.commit()
    }
    fun goToMainActivity(context: Context) {
        var intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }
}
