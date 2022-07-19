package com.example.moodtrackerproject.ui.mood.test_results

import com.example.moodtrackerproject.app.MviAction
import com.example.moodtrackerproject.data.DataBaseRepository

data class TestResultsProps(
    val sumTestPoints: Int,
    val resultPer: Int,
    val action: TestResultsActions? = null,
    val openMood: () -> Unit,
    val testType: Int = 0,
    val stressResults: Int = DataBaseRepository.getTestResults()[0],
    val anxResults: Int = DataBaseRepository.getTestResults()[1]
) {

    sealed class TestResultsActions : MviAction {
        object OpenMood : TestResultsActions()
    }
}
