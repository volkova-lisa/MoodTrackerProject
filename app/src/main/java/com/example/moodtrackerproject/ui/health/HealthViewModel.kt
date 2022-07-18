package com.example.moodtrackerproject.ui.health

import com.example.moodtrackerproject.app.AppState
import com.example.moodtrackerproject.app.HealthState
import com.example.moodtrackerproject.app.MviAction
import com.example.moodtrackerproject.app.Store
import com.example.moodtrackerproject.ui.BaseViewModel
import com.example.moodtrackerproject.ui.health.HealthProps.HealthScreenActions

class HealthViewModel : BaseViewModel<HealthProps>() {

    init {
        setState(Store.appState.healthState)
    }

    override fun map(appState: AppState, action: MviAction?): HealthProps {
        val state = appState.healthState
        return HealthProps(
            action = action as? HealthScreenActions,
            startEdit = {
                setState(
                    state.copy(
                        water = state.water,
                        steps = state.steps,
                        sleep = state.sleep,
                        kcal = state.kcal
                    ),
                    action = HealthScreenActions.StartEditHealthScreen
                )
            },
            water = state.water,
            steps = state.steps,
            sleep = state.sleep,
            kcal = state.kcal
        )
    }

    private fun setState(state: HealthState, action: HealthScreenActions? = null) {
        setState(Store.appState.copy(healthState = state), action)
    }
}
