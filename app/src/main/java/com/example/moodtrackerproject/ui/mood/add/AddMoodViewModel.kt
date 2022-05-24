package com.example.moodtrackerproject.ui.mood.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodtrackerproject.app.Store
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.ui.mood.list.MoodBody
import com.example.moodtrackerproject.utils.DateUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
                    val newList = DataBaseRepository.saveChosenEmoji(it.title)
                    setState(state.copy(listWithChosenMood = moodMap(newList)))
                }
            )
        }
    }

    private fun cancelAdding() {
    }

    private fun addNewMood(pair: Pair<Int, String>) =
        viewModelScope.launch(Dispatchers.Main) {
            val mood = MoodBody(pair.first, pair.second, DateUtils.getDateOfNote())
            DataBaseRepository.insertMood(mood) {
                _addMoodStateLiveData.value = state.copy(action = NewMoodAction.ShowMoodsScreen)
            }
            setState(state.copy(action = NewMoodAction.ShowMoodsScreen))
        }

    private fun setState(newState: AddMoodViewState) {
        Store.setState(newState)
        _addMoodStateLiveData.value = Store.appState.addMoodState
    }
}
