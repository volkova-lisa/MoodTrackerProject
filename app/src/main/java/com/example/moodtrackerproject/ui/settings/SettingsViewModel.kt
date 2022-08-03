package com.example.moodtrackerproject.ui.settings

import com.example.moodtrackerproject.app.AppState
import com.example.moodtrackerproject.app.MviAction
import com.example.moodtrackerproject.app.SettingsState
import com.example.moodtrackerproject.app.Store
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.domain.MaxHealthModel
import com.example.moodtrackerproject.ui.BaseViewModel
import com.example.moodtrackerproject.utils.PreferenceManager
import com.google.firebase.auth.EmailAuthProvider
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
        changePassword = ::changePass
    )

    init {
        setState(Store.appState.settingsState)
        liveData.value = props
    }
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val user = auth.currentUser

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
            healthMax = state.healthMax,
            saveHealthMax = ::saveHealthMax,
            logout = ::logOut,
            isLoggedIn = state.isLoggedIn,
            changePassword = ::changePass
        )
    }

    private fun logOut() {
        auth.signOut()
        PreferenceManager.setInitUser(false)
        setState(Store.appState.settingsState.copy(isLoggedIn = false), action = SettingsProps.SettingsActions.LogOut)
    }

    private fun changePass(currPass: String, newPass: String, confPass: String) {
        if (currPass.isNotEmpty() && newPass.isNotEmpty() && confPass.isNotEmpty()) {
            if (newPass.equals(confPass)) {
                if (user != null && user.email != null) {
                    val credential = EmailAuthProvider.getCredential(user.email!!, currPass.toString())
                    user.reauthenticate(credential).addOnCompleteListener {
                        if (it.isSuccessful) {
                            user.updatePassword(newPass.toString()).addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    props.logout()
                                }
                            }
                        }
                    }
                } else logOut()
            }
        }
    }

    private fun saveHealthMax(w: Int, st: Int, sl: Int, kc: Int) {
        Store.setState(
            Store.appState.healthState.copy(
                healthMax =
                MaxHealthModel(
                    waterMax = w,
                    stepsMax = st,
                    sleepMax = sl,
                    kcalMax = kc
                )
            )
        )
        Store.setState(
            Store.appState.editHealthState.copy(
                healthMax =
                MaxHealthModel(
                    waterMax = w,
                    stepsMax = st,
                    sleepMax = sl,
                    kcalMax = kc
                )
            )
        )
        Store.setState(
            Store.appState.homeState.copy(
                healthMax =
                MaxHealthModel(
                    waterMax = w,
                    stepsMax = st,
                    sleepMax = sl,
                    kcalMax = kc
                )
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
