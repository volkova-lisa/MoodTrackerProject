package com.example.moodtrackerproject.ui.health

import com.example.moodtrackerproject.app.MviAction

data class HealthProps(
    val startEdit: () -> Unit = {},
    val listOfHealth: List<Int> = listOf(1, 1, 1, 1),
    val action: HealthScreenActions? = null,
    val fetchListOfHealth: () -> Unit = {},
    val edited: Boolean = false
) {
    sealed class HealthScreenActions : MviAction {
        object StartEditHealthScreen : HealthScreenActions()
    }
}
