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

    private fun optionMap(option: List<OptionBody>) : List<OptionUiModel> {
        return option.map { model ->
            OptionUiModel(
                text = model.text,
                points = model.points,
                isChecked = model.isChecked,
                checkChanged = {
                    val list = DataBaseRepository.setSelected(it)
                    setState(state.copy(chosenAnswer = optionMap(list)[0]))
                }
            )

        }
    }

    private fun setState(newState: StressTestState) {
        Store.setState(newState)
        _stressStateLiveData.value = Store.appState.stressTestState
    }
}
