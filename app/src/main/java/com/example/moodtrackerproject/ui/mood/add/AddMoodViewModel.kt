package com.example.moodtrackerproject.ui.mood.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moodtrackerproject.ui.mood.list.MoodBody

class AddMoodViewModel : ViewModel() {
    private var state: AddMoodViewState

    init {
        state = AddMoodViewState(
            cancelAdding = ::cancelAdding,
            saveMood = ::addNewMood
        )
    }

    private val _addMoodStateLiveData: MutableLiveData<AddMoodViewState> =
        MutableLiveData<AddMoodViewState>().apply {
            value = state
        }
    val liveData get() = _addMoodStateLiveData

    private fun cancelAdding() {
    }

    private fun addNewMood(mood: MoodBody) {
    }
}
