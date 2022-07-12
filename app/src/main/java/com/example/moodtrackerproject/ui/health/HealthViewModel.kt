package com.example.moodtrackerproject.ui.health

import android.util.Log
import com.example.moodtrackerproject.app.AppState
import com.example.moodtrackerproject.app.HealthState
import com.example.moodtrackerproject.app.MviAction
import com.example.moodtrackerproject.app.Store
import com.example.moodtrackerproject.ui.BaseViewModel

class HealthViewModel : BaseViewModel<HealthProps>() {

    init {
        setState(Store.appState.healthState)
    }

    override fun map(appState: AppState, action: MviAction?): HealthProps {
        val state = appState.healthState
        return HealthProps(
            isEditing = state.isEdited
        )
    }

    private fun setState(state: HealthState, action: HealthProps.HealthScreenActions? = null) {
        setState(Store.appState.copy(healthState = state), action)
    }
}
