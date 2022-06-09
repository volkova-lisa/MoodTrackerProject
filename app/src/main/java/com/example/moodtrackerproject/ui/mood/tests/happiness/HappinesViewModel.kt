package com.example.moodtrackerproject.ui.mood.tests.anger

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.ui.mood.tests.OptionBody
import com.example.moodtrackerproject.ui.mood.tests.OptionUiModel
import com.example.moodtrackerproject.ui.notes.Store

class HappinesViewModel : ViewModel() {
    private var state: HappinesTestState

    init {
        state = Store.appState.happinessTestState.copy(
            setQuestion = ::setNextQuestion,
            moveQuestion = ::nextQuestion,
            again = ::startAgain
        )
        Store.setState(state)
    }

    private val _happinesStateLiveData: MutableLiveData<HappinesTestState> =
        MutableLiveData<HappinesTestState>().apply {
            value = state
        }
    val liveData get() = _happinesStateLiveData

    private fun startAgain() {
        val state =
            Store.appState.happinessTestState.copy(
                currQuestionNum = 0,
                points = 0
            )
        DataBaseRepository.happinessResults = 0
        liveData.value = state
        Store.setState(state)
    }

    private fun setNextQuestion() {
        val num = Store.appState.happinessTestState.currQuestionNum
        val state =
            Store.appState.happinessTestState.copy(
                question = DataBaseRepository.listOfStressQs[num],
                points = num
            )
        liveData.value = state
        Store.setState(state)
    }

    private fun nextQuestion() {
        val num = Store.appState.happinessTestState.currQuestionNum
        val state =
            Store.appState.happinessTestState.copy(
                currQuestionNum = num + 1
            )
        liveData.value = state
        Store.setState(state)
    }

    fun fetchListOfOptions() {
        val options = optionMap(DataBaseRepository.getOptions())
        val state = Store.appState.happinessTestState.copy(listOfOptions = options)
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
                        Store.appState.happinessTestState.copy(
                            chosenAnswer = filtered[0]
                        )
                    )
                    setState(Store.appState.happinessTestState.copy(listOfOptions = optionMap(list)))
                }
            )
        }
    }

    private fun setState(newState: HappinesTestState) {
        Store.setState(newState)
        _happinesStateLiveData.value = Store.appState.happinessTestState
    }
}
