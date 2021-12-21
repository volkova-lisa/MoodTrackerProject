package com.example.moodtrackerproject.routing

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.R

object Routes {

    fun goTo(fragmentActivity: FragmentActivity, fragment: Fragment) {
        val transaction = fragmentActivity.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, fragment)
        transaction.commit()
    }

    fun goToMainActivity(context: Context) {
        var intent = Intent(context.applicationContext, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}
