package com.example.moodtrackerproject.ui.home

import com.example.moodtrackerproject.app.AppState
import com.example.moodtrackerproject.app.MviAction
import com.example.moodtrackerproject.ui.BaseViewModel
import com.example.moodtrackerproject.ui.home.HomeProps.HomeAction
import com.example.moodtrackerproject.utils.PreferenceManager
import com.google.firebase.auth.FirebaseAuth

class HomeViewModel : BaseViewModel<HomeProps>() {

    private val props = HomeProps(
        action = null,
        logout = ::logOut,
    )

    init {
        liveData.value = props
    }

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    private fun logOut() {
        liveData.value = props.copy(isLoggedIn = false)
        auth.signOut()
        PreferenceManager.setInitUser(false)
        liveData.value = props.copy(action = HomeAction.LogOut)
    }

    override fun map(appState: AppState, action: MviAction?): HomeProps {
        TODO("Not yet implemented")
    }
}
