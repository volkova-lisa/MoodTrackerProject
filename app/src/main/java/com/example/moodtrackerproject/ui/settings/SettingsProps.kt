package com.example.moodtrackerproject.ui.settings

import com.example.moodtrackerproject.app.MviAction
import com.example.moodtrackerproject.domain.MaxHealthModel

data class SettingsProps(
    val isLoggedIn: Boolean = false,
    val action: SettingsActions? = null,
    val language: String = "",
    val isDarkOn: Boolean = false,
    val saveLang: (String) -> Unit = {},
    val changePassword: (String, String, String) -> Unit,
    val savePhoto: (String) -> Unit = {},
    val saveName: (String) -> Unit = {},
    val saveMode: (Boolean) -> Unit = {},
    val fetchSettings: () -> Unit = {},
    val name: String = "",
    val email: String = "",
    val photo: String = "",
    val healthMax: MaxHealthModel = MaxHealthModel(),
    val saveHealthMax: (Int, Int, Int, Int) -> Unit,
    val logout: () -> Unit,
) {
    sealed class SettingsActions : MviAction {
        object LogOut : SettingsActions()
    }
}
