package com.example.moodtrackerproject.ui.settings

import com.example.moodtrackerproject.app.MviAction

data class SettingsProps(
    val isLoggedIn: Boolean = false,
    val action: SettingsActions? = null,
    val language: String = "",
    val isDarkOn: Boolean = false,
    val saveLang: (String) -> Unit = {},
    val savePhoto: (String) -> Unit = {},
    val saveName: (String) -> Unit = {},
    val saveMode: (Boolean) -> Unit = {},
    val fetchSettings: () -> Unit = {},
    val name: String = "",
    val email: String = "",
    val photo: String = "",
    val waterMax: Int = 0,
    val stepsMax: Int = 0,
    val sleepMax: Int = 0,
    val kcalMax: Int = 0,
    val saveHealthMax: (Int, Int, Int, Int) -> Unit,
    val logout: () -> Unit,
) {
    sealed class SettingsActions : MviAction {
        object LogOut : SettingsActions()
    }
}
