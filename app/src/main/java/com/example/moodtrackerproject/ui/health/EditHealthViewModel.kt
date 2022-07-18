package com.example.moodtrackerproject.ui.health

import EditHealthProps
import EditHealthProps.*
import android.util.Log
import com.example.moodtrackerproject.app.AppState
import com.example.moodtrackerproject.app.EditHealthState
import com.example.moodtrackerproject.app.MviAction
import com.example.moodtrackerproject.app.Store
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.ui.BaseViewModel

class EditHealthViewModel : BaseViewModel<EditHealthProps>() {

    init {
        setState(Store.appState.editHealthState)
    }

    override fun map(appState: AppState, action: MviAction?): EditHealthProps {
        val state = appState.editHealthState
        return EditHealthProps(
            action = action as? EditHealthScreenActions,
            waterNum = state.waterNum,
            stepsNum = state.stepsNum,
            sleepNum = state.sleepNum,
            kcalNum = state.kcalNum,
            saveEdited = {
                DataBaseRepository.saveHealth(it)
                val list = DataBaseRepository.getHealth()
                Log.d("77777777", DataBaseRepository.getHealth().toString())
                setState(
                    state, action = EditHealthScreenActions.StartHealthScreen
                )
                Store.setState(
                    appState.healthState.copy(
                        water = list[0] as Int,
                        steps = list[1] as Int,
                        sleep = list[2] as Float,
                        kcal = list[3] as Int
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
