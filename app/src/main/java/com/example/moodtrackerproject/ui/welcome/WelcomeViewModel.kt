package com.example.moodtrackerproject.ui.welcome

import com.example.moodtrackerproject.app.AppState
import com.example.moodtrackerproject.app.MviAction
import com.example.moodtrackerproject.ui.BaseViewModel

class WelcomeViewModel : BaseViewModel<WelcomeProps>() {

    private val props = WelcomeProps(
        openHome = ::openHome,
        action = null,
    )

    init {
        liveData.value = props
    }

    private fun openHome() {
        liveData.value = props.copy(action = WelcomeAction.StartHomesScreen)
    }

    override fun map(appState: AppState, action: MviAction?): WelcomeProps {
        TODO("Not yet implemented")
    }
}
