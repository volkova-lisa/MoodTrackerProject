package com.example.moodtrackerproject.ui.health

import EditHealthProps
import EditHealthProps.*
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
        val health = DataBaseRepository.getHealth()
        return EditHealthProps(
            action = action as? EditHealthScreenActions,
            listHealth =
            EditHealthItemProps(
                water = health.water,
                steps = health.steps,
                sleep = health.sleep,
                kcal = health.kcal
            ),
            saveEdited = {
                DataBaseRepository.saveHealth(it)
                setState(
                    state, action = EditHealthScreenActions.StartHealthScreen
                )
                Store.setState(
                    appState.healthState.copy(
                        edited = true
                    )
                )
            },
            startHealth = {
                setState(
                    state,
                    action = EditHealthScreenActions.StartHealthScreen
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
