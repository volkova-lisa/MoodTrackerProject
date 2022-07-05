package com.example.moodtrackerproject.ui.mood.tests

import android.util.Log
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
            setQuestionList = ::setTestQuestions,
            questionText = state.question.text.ifBlank {
                state.questionList[0].text
            },
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

        )
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

        Log.d("++++66666+++", list.toString())

        setState(
            state.copy(
                question = list[state.currQuestionNum],
                points = state.currQuestionNum
            )
        )
    }

    private fun setTestQuestions() {
        val state = Store.appState.stressTestState
        Log.d("++++00000+++", state.testType.toString())
        if (state.testType == 1) {
            setState(
                state.copy(
                    questionList = DataBaseRepository.listOfAnxietyQs
                )
            )
        } else {
            setState(
                state.copy(
                    questionList = DataBaseRepository.listOfStressQs
                )
            )
        }
    }

    private fun savePoints(points: Int) {
        DataBaseRepository.saveStressPoints(points)
        setState(Store.appState.stressTestState.copy(points = points))
    }

    private fun nextQuestion() {
        val state = Store.appState.stressTestState
        val num = Store.appState.stressTestState.currQuestionNum
        setState(state.copy(currQuestionNum = num + 1))
        Log.d("++++++++", state.currQuestionNum.toString())
        // Store.setState(state)
    }

    private fun fetchListOfOptions() {
        val options = DataBaseRepository.getOptions()
        setState(Store.appState.stressTestState.copy(listOfOptions = options))
    }

    private fun setState(state: StressTestState, action: StressTestActions? = null) {
        setState(Store.appState.copy(stressTestState = state), action)
    }
}
