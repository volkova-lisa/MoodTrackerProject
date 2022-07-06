package com.example.moodtrackerproject.ui.mood.test_results

import com.example.moodtrackerproject.app.AppState
import com.example.moodtrackerproject.app.MviAction
import com.example.moodtrackerproject.app.Store
import com.example.moodtrackerproject.app.tests.TestResultsState
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.ui.BaseViewModel
import com.example.moodtrackerproject.ui.mood.test_results.TestResultsProps.TestResultsActions

class TestResultsViewModel : BaseViewModel<TestResultsProps>() {

    init {
        setState(Store.appState.testResultsState)
    }

    override fun map(appState: AppState, action: MviAction?): TestResultsProps {
        val state = appState.testResultsState
        return TestResultsProps(
            sumTestPoints = (DataBaseRepository.getOptions().size * (DataBaseRepository.listOfStressQs.size - 1)),
            resultPer = (DataBaseRepository.stressPoints * 100) / 25,
            action = action as? TestResultsActions,
            openMood = {
                setState(state, action = TestResultsActions.OpenMood)
            },
            testType = state.testType
        )
    }

    private fun setState(state: TestResultsState, action: TestResultsActions? = null) {
        setState(Store.appState.copy(testResultsState = state), action)
    }
}
