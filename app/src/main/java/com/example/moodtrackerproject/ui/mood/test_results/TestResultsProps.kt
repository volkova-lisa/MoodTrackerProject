package com.example.moodtrackerproject.ui.mood.test_results

import com.example.moodtrackerproject.app.MviAction

data class TestResultsProps(
    val sumStressPoints: Int,
    val stressPoints: Int,
    val resultPer: Int,
    val action: TestResultsActions? = null,
    val openMood: () -> Unit,
) {

    sealed class TestResultsActions : MviAction {
        object OpenMood : TestResultsActions()
    }
}
