package com.example.moodtrackerproject.ui.mood.tests.anger

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.ui.mood.tests.OptionBody
import com.example.moodtrackerproject.ui.mood.tests.OptionUiModel
import com.example.moodtrackerproject.ui.notes.Store

class AnxietyTestViewModel : ViewModel() {
    private var state: AnxietyTestState

    init {
        state = Store.appState.anxietyTestState.copy(
            setQuestion = ::setNextQuestion,
            moveQuestion = ::nextQuestion,
            again = ::startAgain
        )
        Store.setState(state)
    }

    private val _anxietyStateLiveData: MutableLiveData<AnxietyTestState> =
        MutableLiveData<AnxietyTestState>().apply {
            value = state
        }
    val liveData get() = _anxietyStateLiveData

    private fun startAgain() {
        val state =
            Store.appState.anxietyTestState.copy(
                currQuestionNum = 0,
                points = 0
            )
        DataBaseRepository.stressPoints = 0
        liveData.value = state
        Store.setState(state)
    }

    private fun setNextQuestion() {
        val num = Store.appState.anxietyTestState.currQuestionNum
        val state =
            Store.appState.anxietyTestState.copy(
                question = DataBaseRepository.listOfStressQs[num],
                points = num
            )
        liveData.value = state
        Store.setState(state)
    }

    private fun nextQuestion() {
        val num = Store.appState.anxietyTestState.currQuestionNum
        val state =
            Store.appState.anxietyTestState.copy(
                currQuestionNum = num + 1
            )
        liveData.value = state
        Store.setState(state)
    }

    fun fetchListOfOptions() {
        val options = optionMap(DataBaseRepository.getOptions())
        val state = Store.appState.anxietyTestState.copy(listOfOptions = options)
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
                        Store.appState.anxietyTestState.copy(
                            chosenAnswer = filtered[0]
                        )
                    )
                    setState(Store.appState.anxietyTestState.copy(listOfOptions = optionMap(list)))
                }
            )
        }
    }

    private fun setState(newState: AnxietyTestState) {
        Store.setState(newState)
        _anxietyStateLiveData.value = Store.appState.anxietyTestState
    }
}
