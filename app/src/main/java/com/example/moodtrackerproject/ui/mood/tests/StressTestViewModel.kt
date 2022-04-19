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
        liveData.value =
            state.copy(
                question = DataBaseRepository.listOfStressQs[num],
                points = num
            )
        Store.setState(liveData.value!!)
    }

    fun fetchListOfOptions() {
        val options = optionMap(DataBaseRepository.getOptions())
        Log.d("----state------", DataBaseRepository.getOptions().toString())
        setState(state.copy(listOfOptions = options))
    }

    private fun optionMap(option: List<OptionBody>): List<OptionUiModel> {
        return option.map { model ->
            OptionUiModel(
                text = model.text,
                points = model.points,
                isChecked = model.isChecked,
                checkChanged = {
                    val list = DataBaseRepository.setSelected(it)
                    Log.d("000000list", list.toString())
                    Log.d("111111list", optionMap(list)[0].toString())
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
