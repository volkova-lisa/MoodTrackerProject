package com.example.moodtrackerproject.ui.mood.tests

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moodtrackerproject.app.Store
import com.example.moodtrackerproject.data.DataBaseRepository

class StressTestViewModel : ViewModel() {

    private var state: StressTestState

    init {
        state = Store.appState.stressTestState.copy(
            setQuestion = ::setNextQuestion,
            moveQuestion = ::nextQuestion,
            again = ::startAgain
        )
        Store.setState(state)
    }

    private val _stressStateLiveData: MutableLiveData<StressTestState> =
        MutableLiveData<StressTestState>().apply {
            value = state
        }
    val liveData get() = _stressStateLiveData

    private fun startAgain() {
        val state =
            Store.appState.stressTestState.copy(
                currQuestionNum = 0,
                points = 0
            )
        DataBaseRepository.stressPoints = 0
        liveData.value = state
        Store.setState(state)
    }

    private fun setNextQuestion() {
        val num = Store.appState.stressTestState.currQuestionNum
        val state =
            Store.appState.stressTestState.copy(
                question = DataBaseRepository.listOfStressQs[num],
                points = num
            )
        liveData.value = state
        Store.setState(state)
    }

    private fun nextQuestion() {
        val num = Store.appState.stressTestState.currQuestionNum
        val state =
            Store.appState.stressTestState.copy(
                currQuestionNum = num + 1
            )
        liveData.value = state
        Store.setState(state)
    }

    fun fetchListOfOptions() {
        val options = optionMap(DataBaseRepository.getOptions())
        val state = Store.appState.stressTestState.copy(listOfOptions = options)
        liveData.value = state
        Store.setState(state)
    }

    private fun optionMap(option: List<OptionBody>): List<OptionUiModel> {
        return option.map { model ->
            OptionUiModel(
                text = model.text,
                points = model.points,
                isChecked = model.isChecked,
                checkChanged = {
                    val list = DataBaseRepository.saveSelected(it)
                    val filtered = optionMap(list).filter { it.isChecked == true }
                    setState(
                        Store.appState.stressTestState.copy(
                            chosenAnswer = filtered[0]
                        )
                    )
                    setState(Store.appState.stressTestState.copy(listOfOptions = optionMap(list)))
                }
            )
        }
    }

    private fun setState(newState: StressTestState) {
        Store.setState(newState)
        _stressStateLiveData.value = Store.appState.stressTestState
    }
}
