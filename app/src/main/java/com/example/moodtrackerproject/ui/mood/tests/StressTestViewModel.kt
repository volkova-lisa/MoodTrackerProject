package com.example.moodtrackerproject.ui.mood.tests

import com.example.moodtrackerproject.app.AppState
import com.example.moodtrackerproject.app.MviAction
import com.example.moodtrackerproject.app.Store
import com.example.moodtrackerproject.app.tests.StressTestState
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.ui.BaseViewModel
import com.example.moodtrackerproject.ui.mood.tests.StressTestProps.StressTestActions

class StressTestViewModel : BaseViewModel<StressTestProps>() {

    init {
        setState(Store.appState.stressTestState)
    }

    override fun map(appState: AppState, action: MviAction?): StressTestProps {
        val state = appState.stressTestState
        return StressTestProps(
            setQuestion = ::setNextQuestion,
            moveQuestion = ::nextQuestion,
            again = ::startAgain,
            fetchListOfOptions = ::fetchListOfOptions,
            questionList = state.questionList,
            listOfOptions = state.listOfOptions.map { model ->
                StressTestProps.OptionItemProps(
                    text = model.text,
                    points = model.points,
                    isChecked = model.isChecked,
                    checkChanged = {
                        val list = DataBaseRepository.saveSelected(model.text)
                        setState(state.copy(listOfOptions = list))
                    }
                )
            },
            stressQuestionsQty = DataBaseRepository.listOfStressQs.size - 1,
            points = state.points,
            currQuestionNum = state.currQuestionNum,
            openMood = {
                setState(state, action = StressTestActions.OpenMood)
            },
            openResults = {
                setState(state, action = StressTestActions.OpenResults)
            },
            savePoints = ::savePoints,
            action = action as? StressTestActions,
            curTestType = state.testType,
            shareTestType = ::shareTestType
        )
    }

    private fun shareTestType(type: Int) {
        val state = Store.appState.testResultsState
        if (type == 1) {
            Store.setState(
                state.copy(
                    questionList = DataBaseRepository.listOfAnxietyQs
                )
            )
        } else {
            Store.setState(
                state.copy(
                    questionList = DataBaseRepository.listOfStressQs
                )
            )
        }
    }

    private fun startAgain() {
        DataBaseRepository.stressPoints = 0
        setState(
            Store.appState.stressTestState.copy(
                currQuestionNum = 0,
                points = 0
            )
        )
    }

    private fun setNextQuestion() {
        val state = Store.appState.stressTestState
        val list = state.questionList

        setState(
            state.copy(
                question = list[state.currQuestionNum],
                points = state.currQuestionNum
            )
        )
    }

    private fun savePoints(points: Int) {
        DataBaseRepository.saveStressPoints(points)
        setState(Store.appState.stressTestState.copy(points = points))
    }

    private fun nextQuestion() {
        val state = Store.appState.stressTestState
        val num = Store.appState.stressTestState.currQuestionNum
        setState(state.copy(currQuestionNum = num + 1))
    }

    private fun fetchListOfOptions() {
        val options = DataBaseRepository.getOptions()
        setState(Store.appState.stressTestState.copy(listOfOptions = options))
    }

    private fun setState(state: StressTestState, action: StressTestActions? = null) {
        setState(Store.appState.copy(stressTestState = state), action)
    }
}
