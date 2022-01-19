package com.example.moodtrackerproject.routing

import android.os.Bundle
import androidx.navigation.NavController
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.R

class Router(private val activity: MainActivity) {
    // TODO: use constants for routes
    private lateinit var navController: NavController // = activity.findNavController(R.id.nav_host_fragment)

    // TODO: i'm sure this shouldn't be here
    fun openDetails(id: String) {
        val bundle = Bundle()
        bundle.putString("ID", id)
        navController.navigate(R.id.noteDetailsFragment, bundle)
    }

    fun setNavigationController(controller: NavController) {
        navController = controller
    }

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
        navController.navigate(R.id.noteDetailsFragment)
    }
}
