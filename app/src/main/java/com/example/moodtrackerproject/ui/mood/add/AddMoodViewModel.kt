package com.example.moodtrackerproject.ui.mood.add

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.ui.mood.list.MoodBody
import com.example.moodtrackerproject.ui.notes.Store

class AddMoodViewModel : ViewModel() {
    private var state: AddMoodViewState

    init {
        state = Store.appState.addMoodState.copy(
            cancelAdding = ::cancelAdding,
            saveMood = ::addNewMood
        )
        Store.setState(state)
    }

    private val _addMoodStateLiveData: MutableLiveData<AddMoodViewState> =
        MutableLiveData<AddMoodViewState>().apply {
            value = state
        }
    val liveData get() = _addMoodStateLiveData

    fun fetchListOfMoods() {
        val moodsList = moodMap(DataBaseRepository.getEmojiList())
        setState(state.copy(listWithChosenMood = moodsList))
    }

    private fun moodMap(emojiList: List<EmojiBody>): List<EmojiBodyUIModel> {
        return emojiList.map { model ->
            EmojiBodyUIModel(
                image = model.image,
                title = model.title,
                isChecked = model.isChecked,
                checkChanged = {
                    val newList = DataBaseRepository.setChosenEmoji(it.title)
                    setState(state.copy(listWithChosenMood = moodMap(newList)))
                    Log.d("-----checkChanged", state.listWithChosenMood.toString())
                }
            )
        }
    }

    private fun cancelAdding() {
    }

    private fun addNewMood(mood: MoodBody) {
    }

    private fun setState(newState: AddMoodViewState) {
        Store.setState(newState)
        _addMoodStateLiveData.value = Store.appState.addMoodState
    }
}
