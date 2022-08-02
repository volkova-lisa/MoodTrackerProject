package com.example.moodtrackerproject.ui.health

import com.example.moodtrackerproject.app.MviAction

data class HealthProps(
    val startEdit: () -> Unit = {},
    val action: HealthScreenActions? = null,
    val fetchListOfHealth: () -> Unit = {},
    val edited: Boolean = false,
    val healthItems: HealthItemProps? = null

) {
    sealed class HealthScreenActions : MviAction {
        object StartEditHealthScreen : HealthScreenActions()
    }

    data class HealthItemProps(
        val water: Int = 0,
        val steps: Int = 0,
        val sleep: Int = 0,
        val kcal: Int = 0,
        val waterMax: Int = 0,
        val stepsMax: Int = 0,
        val sleepMax: Int = 0,
        val kcalMax: Int = 0
    )
}
