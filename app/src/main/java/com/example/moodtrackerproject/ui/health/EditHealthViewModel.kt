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
            waterNum = DataBaseRepository.getHealth()[0],
            stepsNum = DataBaseRepository.getHealth()[1],
            sleepNum = DataBaseRepository.getHealth()[2],
            kcalNum = DataBaseRepository.getHealth()[3],
            saveEdited = {
                DataBaseRepository.saveHealth(it)
                val list = DataBaseRepository.getHealth()
                Log.d("77777777", DataBaseRepository.getHealth().toString())
                setState(
                    state, action = EditHealthScreenActions.StartHealthScreen
                )
                Store.setState(
                    appState.healthState.copy(
                        water = list[0],
                        steps = list[1],
                        sleep = list[2],
                        kcal = list[3]
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
