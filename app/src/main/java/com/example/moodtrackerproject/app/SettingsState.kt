package com.example.moodtrackerproject.app

data class SettingsState(
    val language: String = "",
    val isDarkOn: Boolean = false,
    val name: String = "",
    val email: String = "",
    val photo: String = ""
)
