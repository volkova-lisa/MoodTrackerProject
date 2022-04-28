package com.example.moodtrackerproject.ui.mood.tests

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.ui.notes.Store

class StressTestViewModel : ViewModel() {

    private var state: StressTestState

    init {
        state = StressTestState(
            setQuestion = ::setQuestion,
        )
        Store.setState(state)
    }
    private val _stressStateLiveData: MutableLiveData<StressTestState> =
        MutableLiveData<StressTestState>().apply {
            value = state
        }
    val liveData get() = _stressStateLiveData

    private fun setQuestion(num: Int) {
        liveData.value =
            state.copy(
                question = DataBaseRepository.listOfStressQs[num],
                points = num
            )
        Store.setState(liveData.value!!)
    }

    fun fetchListOfOptions() {
        val options = optionMap(DataBaseRepository.getOptions())
        liveData.value = state.copy(listOfOptions = options)
        Store.setState(liveData.value!!)
    }

    private fun optionMap(option: List<OptionBody>): List<OptionUiModel> {
        return option.map { model ->
            OptionUiModel(
                text = model.text,
                points = model.points,
                isChecked = model.isChecked,
                checkChanged = {
                    val list = DataBaseRepository.setSelected(it)
                    val filtered = optionMap(list).filter { it.isChecked == true }
                    setState(
                        state.copy(
                            chosenAnswer = filtered[0]
                        )
                    )
                    setState(state.copy(listOfOptions = optionMap(list)))
                }
            )
        }
    }
    private fun setState(newState: StressTestState) {
        Store.setState(newState)
        _stressStateLiveData.value = Store.appState.stressTestState
    }
}
