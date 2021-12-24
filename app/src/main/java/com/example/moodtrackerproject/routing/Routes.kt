package com.example.moodtrackerproject.routing

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.SplashActivity

class Routes(private val activity: MainActivity) {

//    private val activity: MainActivity

    companion object {

        @Volatile private var INSTANCE: Routes? = null

        fun getInstance(activity: MainActivity): Routes =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Routes(activity)
            }
    }

    fun goTo(fragmentActivity: FragmentActivity, fragment: Fragment) {
        val transaction = fragmentActivity.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, fragment)
        transaction.commit()
    }

    fun goToMainActivity(activity: SplashActivity) {
        var intent = Intent(activity.applicationContext, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        activity.startActivity(intent)
    }

//    fun openLoginFromWelcome() {
//        activity.navController.navigate(R.id.action_welcomeFragment_to_loginFragment)
//    }
//
//    fun openNotesScreen() {
//        activity.navController.navigate(R.id.action_loginFragment_to_notesFragment)
//    }
//
//    fun openRegistration() {
//        activity.navController.navigate(R.id.action_loginFragment_to_registrationFragment)
//    }
//
//    fun openLoginFromRegistration() {
//        activity.navController.navigate(R.id.action_registrationFragment_to_loginFragment)
//    }
//
//    fun openResetPassScreen() {
//        activity.navController.navigate(R.id.action_loginFragment_to_resetPasswordFragment)
//    }
//
}
