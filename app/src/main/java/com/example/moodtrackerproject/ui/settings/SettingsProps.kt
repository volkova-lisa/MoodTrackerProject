package com.example.moodtrackerproject.ui.settings

import com.example.moodtrackerproject.app.MviAction

data class SettingsProps(
    val lang: Int = 0,
) {
    sealed class SettingsActions : MviAction
}
