package com.example.moodtrackerproject.ui.health

import com.example.moodtrackerproject.app.MviAction

data class HealthProps(
    val startEdit: () -> Unit = {},
    val water: Int = 0,
    val steps: Int = 0,
    val sleep: Int = 0,
    val kcal: Int = 0,
    val action: HealthScreenActions? = null
) {
    sealed class HealthScreenActions : MviAction {
        object StartEditHealthScreen : HealthScreenActions()
    }
}
