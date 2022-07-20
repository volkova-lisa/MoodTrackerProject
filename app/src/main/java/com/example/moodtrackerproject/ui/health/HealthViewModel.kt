package com.example.moodtrackerproject.ui.health

import com.example.moodtrackerproject.app.AppState
import com.example.moodtrackerproject.app.HealthState
import com.example.moodtrackerproject.app.MviAction
import com.example.moodtrackerproject.app.Store
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.ui.BaseViewModel
import com.example.moodtrackerproject.ui.health.HealthProps.HealthScreenActions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
                    state,
                    action = HealthScreenActions.StartEditHealthScreen
                )
            },
            listOfHealth =
            if (state.listOfHealth != null) {
                HealthProps.HealthItemProps(
                    water = state.listOfHealth.water,
                    steps = state.listOfHealth.steps,
                    sleep = state.listOfHealth.sleep,
                    kcal = state.listOfHealth.kcal
                )
            } else null,

            fetchListOfHealth = ::fetchListOfHealth,
            edited = state.edited
        )
    }

    private fun fetchListOfHealth() {
        launch {
            val health = withContext(Dispatchers.IO) {
                DataBaseRepository.getHealth()
            }
            setState(Store.appState.healthState.copy(listOfHealth = health))
        }
    }

    private fun setState(state: HealthState, action: HealthScreenActions? = null) {
        setState(Store.appState.copy(healthState = state), action)
    }
}
