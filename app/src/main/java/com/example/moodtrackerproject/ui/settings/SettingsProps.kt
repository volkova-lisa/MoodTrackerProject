package com.example.moodtrackerproject.ui.settings

import com.example.moodtrackerproject.app.MviAction

data class SettingsProps(
    val language: String = "",
    val isDarkOn: Boolean = false,
    val saveLang: (String) -> Unit = {},
    val savePhoto: (ByteArray) -> Unit = {},
    val saveName: (String) -> Unit = {},
    val saveMode: (Boolean) -> Unit = {},
    val fetchSettings: () -> Unit = {},
    val name: String = "",
    val email: String = "",
) {
    sealed class SettingsActions : MviAction
}
