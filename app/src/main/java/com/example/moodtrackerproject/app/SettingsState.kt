package com.example.moodtrackerproject.app

import com.example.moodtrackerproject.domain.MaxHealthModel

data class SettingsState(
    val language: String = "",
    val isDarkOn: Boolean = false,
    val name: String = "",
    val email: String = "",
    val photo: String = "",
    val isLoggedIn: Boolean = false,
    val healthMax: MaxHealthModel = MaxHealthModel(),
)
