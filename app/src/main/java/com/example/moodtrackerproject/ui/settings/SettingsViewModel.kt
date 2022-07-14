package com.example.moodtrackerproject.ui.settings

import com.example.moodtrackerproject.app.AppState
import com.example.moodtrackerproject.app.MviAction
import com.example.moodtrackerproject.ui.BaseViewModel

class SettingsViewModel : BaseViewModel<SettingsProps>() {
    init {
    }

    override fun map(appState: AppState, action: MviAction?): SettingsProps {
        val state = appState.settingsState
        return SettingsProps(
            lang = state.language
        )
    }

    private fun setState() {
    }
}
