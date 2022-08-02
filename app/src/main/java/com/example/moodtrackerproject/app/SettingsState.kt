package com.example.moodtrackerproject.app

data class SettingsState(
    val language: String = "",
    val isDarkOn: Boolean = false,
    val name: String = "",
    val email: String = "",
    val photo: String = "",
    val isLoggedIn: Boolean = false,
    val waterMax: Int = 0,
    val stepsMax: Int = 0,
    val sleepMax: Int = 0,
    val kcalMax: Int = 0,
)
