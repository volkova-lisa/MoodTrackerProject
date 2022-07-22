package com.example.moodtrackerproject.ui.mood.test_results

import com.example.moodtrackerproject.app.MviAction
import com.example.moodtrackerproject.domain.ResultsModel

data class TestResultsProps(
    val sumTestPoints: Int,
    val resultPer: Int,
    val action: TestResultsActions? = null,
    val openMood: () -> Unit,
    val testType: Int = 0,
    val testResults: ResultsModel? = null,
    val fetchTestResults: () -> Unit = {}
) {

    sealed class TestResultsActions : MviAction {
        object OpenMood : TestResultsActions()
    }

    data class ResultsItemProps(
        val stress: Int = 0,
        val anxiety: Int = 0
    )
}
