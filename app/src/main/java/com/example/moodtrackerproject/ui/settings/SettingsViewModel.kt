package com.example.moodtrackerproject.ui.settings

import com.example.moodtrackerproject.app.AppState
import com.example.moodtrackerproject.app.MviAction
import com.example.moodtrackerproject.app.SettingsState
import com.example.moodtrackerproject.app.Store
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingsViewModel : BaseViewModel<SettingsProps>() {

    private val props = SettingsProps(
        fetchSettings = ::fetchSettings,
        language = DataBaseRepository.getLang()
    )

    init {
        setState(Store.appState.settingsState)
        liveData.value = props
    }

    override fun map(appState: AppState, action: MviAction?): SettingsProps {
        val state = appState.settingsState
        return SettingsProps(
            language = state.language,
            isDarkOn = state.isDarkOn,
            saveLang = {
                DataBaseRepository.saveLanguage(it)
                setState(appState.settingsState.copy(language = it))
            },
            saveMode = {
                DataBaseRepository.saveMode(it)
            },
            saveName = {
                DataBaseRepository.saveName(it)
                Store.setState(appState.homeState.copy(name = DataBaseRepository.getName()))
            },
            fetchSettings = ::fetchSettings,
            name = state.name,
            email = state.email
        )
    }

    fun fetchSettings() {
        launch {
            val lang = withContext(Dispatchers.IO) {
                DataBaseRepository.getLang()
            }
            val darkOn = withContext(Dispatchers.IO) {
                DataBaseRepository.getMode()
            }
            val name = withContext(Dispatchers.IO) {
                DataBaseRepository.getName()
            }
            val email = withContext(Dispatchers.IO) {
                DataBaseRepository.getEmail()
            }
            setState(
                Store.appState.settingsState.copy(
                    language = lang, isDarkOn = darkOn,
                    name = name, email = email
                )
            )
        }
    }

    private fun setState(state: SettingsState, action: SettingsProps.SettingsActions? = null) {
        setState(Store.appState.copy(settingsState = state), action)
    }
}
