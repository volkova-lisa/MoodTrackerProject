package com.example.moodtrackerproject.ui.health

import com.example.moodtrackerproject.app.MviAction

data class HealthProps(
    val isEditing: Boolean = false,
) {
    sealed class HealthScreenActions : MviAction {
        object StartEditHealthScreen : HealthProps.HealthScreenActions()
    }
}
