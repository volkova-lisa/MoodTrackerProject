package com.example.moodtrackerproject.ui.health

import com.example.moodtrackerproject.app.MviAction
import com.example.moodtrackerproject.data.DataBaseRepository

data class HealthProps(
    val startEdit: () -> Unit = {},
    val listOfHealth: List<Int> = listOf(1, 1, 1, 1),
    val action: HealthScreenActions? = null,
    val fetchListOfHealth: () -> Unit = {},
    val edited: Boolean = false,
    val currWater: Int = DataBaseRepository.getHealth()[0],
    val currSteps: Int = DataBaseRepository.getHealth()[1],
    val currSleep: Int = DataBaseRepository.getHealth()[2],
    val currKcal: Int = DataBaseRepository.getHealth()[3]

) {
    sealed class HealthScreenActions : MviAction {
        object StartEditHealthScreen : HealthScreenActions()
    }
}
