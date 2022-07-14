package com.example.moodtrackerproject.ui.health

import EditHealthProps
import com.example.moodtrackerproject.app.AppState
import com.example.moodtrackerproject.app.EditHealthState
import com.example.moodtrackerproject.app.MviAction
import com.example.moodtrackerproject.app.Store
import com.example.moodtrackerproject.ui.BaseViewModel

class EditHealthViewModel : BaseViewModel<EditHealthProps>() {

    init {
        setState(Store.appState.editHealthState)
    }

    override fun map(appState: AppState, action: MviAction?): EditHealthProps {
        val state = appState.editHealthState
        return EditHealthProps(
            waterNum = state.waterNum,
            stepsNum = state.stepsNum,
            sleepNum = state.sleepNum,
            kcalNum = state.kcalNum
        )
    }

    private fun setState(state: EditHealthState, action: EditHealthProps.EditHealthScreenActions? = null) {
        setState(Store.appState.copy(editHealthState = state), action)
    }
}
