package com.example.moodtrackerproject.ui.settings

import android.util.Log
import com.example.moodtrackerproject.app.AppState
import com.example.moodtrackerproject.app.MviAction
import com.example.moodtrackerproject.app.SettingsState
import com.example.moodtrackerproject.app.Store
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.domain.MaxHealthModel
import com.example.moodtrackerproject.ui.BaseViewModel
import com.example.moodtrackerproject.utils.PreferenceManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingsViewModel : BaseViewModel<SettingsProps>() {

    private val props = SettingsProps(
        action = null,
        fetchSettings = ::fetchSettings,
        language = DataBaseRepository.getLang(),
        saveHealthMax = ::saveHealthMax,
        logout = ::logOut,
    )

    init {
        setState(Store.appState.settingsState)
        liveData.value = props
    }
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun map(appState: AppState, action: MviAction?): SettingsProps {
        val state = appState.settingsState
        return SettingsProps(
            action = action as? SettingsProps.SettingsActions,
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
            savePhoto = {
                DataBaseRepository.savePhoto(it)
                Store.setState(appState.settingsState.copy(photo = DataBaseRepository.getPhoto()))
            },
            fetchSettings = ::fetchSettings,
            name = state.name,
            email = state.email,
            photo = state.photo,
            waterMax = state.waterMax,
            stepsMax = state.stepsMax,
            sleepMax = state.sleepMax,
            kcalMax = state.kcalMax,
            saveHealthMax = ::saveHealthMax,
            logout = ::logOut,
            isLoggedIn = state.isLoggedIn,
        )
    }

    private fun logOut() {
        auth.signOut()
        PreferenceManager.setInitUser(false)
        setState(Store.appState.settingsState.copy(isLoggedIn = false), action = SettingsProps.SettingsActions.LogOut)
    }

    private fun saveHealthMax(w: Int, st: Int, sl: Int, kc: Int) {
        Store.setState(
            Store.appState.healthState.copy(
                waterMax = w,
                stepsMax = st,
                sleepMax = sl,
                kcalMax = kc
            )
        )
        Store.setState(
            Store.appState.editHealthState.copy(
                waterMax = w,
                stepsMax = st,
                sleepMax = sl,
                kcalMax = kc
            )
        )
        Store.setState(
            Store.appState.homeState.copy(
                waterMax = w,
                stepsMax = st,
                sleepMax = sl,
                kcalMax = kc
            )
        )
        DataBaseRepository.saveHealthMax(MaxHealthModel(w, st, sl, kc))
    }

    private fun fetchSettings() {
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
            val photo = withContext(Dispatchers.IO) {
                DataBaseRepository.getPhoto()
            }
            setState(
                Store.appState.settingsState.copy(
                    language = lang, isDarkOn = darkOn,
                    name = name, email = email, photo = photo
                )
            )
        }
    }

    private fun setState(state: SettingsState, action: SettingsProps.SettingsActions? = null) {
        setState(Store.appState.copy(settingsState = state), action)
    }
}
