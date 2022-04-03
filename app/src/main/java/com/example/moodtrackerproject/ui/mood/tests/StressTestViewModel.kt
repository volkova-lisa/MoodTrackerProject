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
        setState(state)
    }
    private val _stressStateLiveData: MutableLiveData<StressTestState> =
        MutableLiveData<StressTestState>().apply {
            value = state
        }
    val liveData get() = _stressStateLiveData

    private fun setQuestion(num: Int) {
        liveData.value =
            state.copy(
                chosenAnswer = 0,
                question = DataBaseRepository.listOfStressQs[num],
                questionNum = num,
                points = num
            )

        _stressStateLiveData.value = Store.appState.stressTestState
    }

    private fun setState(newState: StressTestState) {
        Store.setState(newState)
        _stressStateLiveData.value = Store.appState.stressTestState
    }
}
