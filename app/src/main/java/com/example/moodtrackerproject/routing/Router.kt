package com.example.moodtrackerproject.routing

import androidx.navigation.NavController
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.R

class Router(private val activity: MainActivity) {
    // TODO: use constants for routes
    private lateinit var navController: NavController // = activity.findNavController(R.id.nav_host_fragment)

    fun openSettings() {
        navController.navigate(R.id.settingsFragment)
    }

    fun openHealth() {
        navController.navigate(R.id.healthFragment)
    }

    fun openAddMood() {
        navController.navigate(R.id.action_moodFragment_to_addMoodFragment)
    }

    fun openAddMoodHome() {
        navController.navigate(R.id.addMoodFragment)
    }

    fun openEditHealth() {
        navController.navigate(R.id.editHealthFragment)
    }

    fun openResults() {
        navController.navigate(R.id.testResults)
    }

    fun openDetails() {
        navController.navigate(R.id.noteDetailsFragment)
    }

    fun setNavigationController(controller: NavController) {
        navController = controller
    }

    fun openMood() {
        navController.navigate(R.id.moodFragment)
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

    fun openStressTest() {
        navController.navigate(R.id.stressTest)
    }
}
