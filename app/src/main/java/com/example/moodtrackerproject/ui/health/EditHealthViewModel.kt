package com.example.moodtrackerproject.ui.health

import EditHealthProps
import EditHealthProps.*
import android.util.Log
import com.example.moodtrackerproject.app.AppState
import com.example.moodtrackerproject.app.EditHealthState
import com.example.moodtrackerproject.app.MviAction
import com.example.moodtrackerproject.app.Store
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.domain.MaxHealthModel
import com.example.moodtrackerproject.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditHealthViewModel : BaseViewModel<EditHealthProps>() {

    val props = EditHealthProps(
        fetchMaxHealth = ::fetchMaxHealth,
    )

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
                kcal = health.kcal,
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
            },
            fetchMaxHealth = ::fetchMaxHealth,
            healthMax = state.healthMax
        )
    }

    private fun fetchMaxHealth() {
        launch {
            val maxHealth = withContext(Dispatchers.IO) {
                DataBaseRepository.getHealthMax()
            }
            setState(
                Store.appState.editHealthState.copy(
                    healthMax =
                    MaxHealthModel(
                        waterMax = maxHealth.waterMax,
                        stepsMax = maxHealth.stepsMax,
                        sleepMax = maxHealth.sleepMax,
                        kcalMax = maxHealth.kcalMax
                    )
                )
            )
        }
    }

    private fun setState(
        state: EditHealthState,
        action: EditHealthScreenActions? = null
    ) {
        setState(Store.appState.copy(editHealthState = state), action)
    }
}
