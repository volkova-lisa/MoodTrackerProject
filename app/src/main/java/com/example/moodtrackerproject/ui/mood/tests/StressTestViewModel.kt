package com.example.moodtrackerproject.ui.mood.tests

import android.util.Log
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
        Log.d("0000000", "state.question.text")
        liveData.value =
            state.copy(
                chosenAnswer = 0,
                question = DataBaseRepository.listOfStressQs[num],
                questionNum = num,
                points = num
            )
        Log.d("0100000", "state.question.text")
        Store.setState(liveData.value!!)
    }

    private fun setState(newState: StressTestState) {
        Store.setState(newState)
        _stressStateLiveData.value = Store.appState.stressTestState
    }
}
