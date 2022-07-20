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
        return EditHealthProps(
            action = action as? EditHealthScreenActions,
            listHealth =
            EditHealthItemProps(
                water = DataBaseRepository.getHealth().water,
                steps = DataBaseRepository.getHealth().steps,
                sleep = DataBaseRepository.getHealth().sleep,
                kcal = DataBaseRepository.getHealth().kcal
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
