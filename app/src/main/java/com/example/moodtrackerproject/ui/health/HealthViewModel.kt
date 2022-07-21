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
            if (state.healthModel != null) {
                HealthProps.HealthItemProps(
                    water = state.healthModel.water,
                    steps = state.healthModel.steps,
                    sleep = state.healthModel.sleep,
                    kcal = state.healthModel.kcal
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
            setState(Store.appState.healthState.copy(healthModel = health))
        }
    }

    private fun setState(state: HealthState, action: HealthScreenActions? = null) {
        setState(Store.appState.copy(healthState = state), action)
    }
}
