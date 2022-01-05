package com.example.moodtrackerproject.routing

import androidx.navigation.NavController
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.R

class Router(private val activity: MainActivity) {

//    private val activity: MainActivity

    lateinit var navController: NavController // = activity.findNavController(R.id.nav_host_fragment)

    fun setNavigationController(controller: NavController) {
        navController = controller
    }
//    companion object {
//
//        @Volatile private var INSTANCE: Router? = null
//
//        fun getInstance(activity: MainActivity): Router =
//            INSTANCE ?: synchronized(this) {
//                INSTANCE ?: Router(activity)
//            }
//    }
//
//    fun goTo(fragmentActivity: FragmentActivity, fragment: Fragment) {
//        val transaction = fragmentActivity.supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.nav_host_fragment, fragment)
//        transaction.commit()
//    }
//
//    fun goToMainActivity(activity: SplashActivity) {
//        var intent = Intent(activity.applicationContext, MainActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//        activity.startActivity(intent)
//    }

    fun openWelcome() {
        navController.navigate(R.id.welcomeFragment)
    }

    fun openHome() {
        navController.navigate(R.id.homeFragment)
    }

    fun openLoginFromWelcome() {
        navController.navigate(R.id.action_welcomeFragment_to_loginFragment)
    }

    fun openNotesScreen() {
        navController.navigate(R.id.notesFragment)
    }

    fun openAddNewNote() {
        navController.navigate(R.id.addNewNoteFragment)
    }

    fun openRegistration() {
        navController.navigate(R.id.action_loginFragment_to_registrationFragment)
    }

    fun openLoginFromRegistration() {
        navController.navigate(R.id.action_registrationFragment_to_loginFragment)
    }

    fun openResetPassScreen() {
        navController.navigate(R.id.action_loginFragment_to_resetPasswordFragment)
    }

    fun openInsideNote() {
        navController.navigate(R.id.insideNoteFragment)
    }
}
