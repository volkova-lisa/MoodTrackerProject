package com.example.moodtrackerproject.ui.health

import EditHealthProps
import EditHealthProps.*
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
            kcalNum = state.kcalNum,
            saveEdited = {
                setState(
                    state.copy(
                        waterNum = it[0] as Int,
                        stepsNum = it[1] as Int,
                        sleepNum = it[2] as Float,
                        kcalNum = it[3] as Int
                    ),
                    action = EditHealthScreenActions.StartHealthScreen
                )
                Store.setState(
                    appState.healthState.copy(
                        water = it[0] as Int,
                        steps = it[1] as Int,
                        sleep = it[2] as Float,
                        kcal = it[3] as Int
                    )
                )
            }
        )
    }

    private fun setState(
        state: EditHealthState,
        action: EditHealthScreenActions? = null
    ) {
        setState(Store.appState.copy(editHealthState = state), action)
    }
}
