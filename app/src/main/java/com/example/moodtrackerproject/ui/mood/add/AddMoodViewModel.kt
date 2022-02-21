package com.example.moodtrackerproject.ui.mood.add

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.ui.mood.list.MoodBody

class AddMoodViewModel : ViewModel() {
    private var state: AddMoodViewState

    init {
        state = AddMoodViewState(
            cancelAdding = ::cancelAdding,
            saveMood = ::addNewMood,
            listOfMoods = listOf(
                EmojiBody(R.drawable.emoji_anger, "anger"), EmojiBody(R.drawable.emoji_awesome, "awesome"),
                EmojiBody(R.drawable.emoji_calm, "calm"), EmojiBody(R.drawable.emoji_concentrated, "concentrated"),
                EmojiBody(R.drawable.emoji_cool, "cool"), EmojiBody(R.drawable.emoji_crying, "crying"),
                EmojiBody(R.drawable.emoji_crying_inside, "crying inside"), EmojiBody(R.drawable.emoji_excited, "excited"),
                EmojiBody(R.drawable.emoji_furious, "furious"), EmojiBody(R.drawable.emoji_great, "great"),
                EmojiBody(R.drawable.emoji_puking, "puking"), EmojiBody(R.drawable.emoji_sad, "sad"),
                EmojiBody(R.drawable.emoji_shocked, "shocked"), EmojiBody(R.drawable.emoji_sick, "sick"),
                EmojiBody(R.drawable.emoji_sleepy, "sleepy"), EmojiBody(R.drawable.emoji_traumatised, "traumatised"),
            )
        )
    }

    fun fetchListOfMoods() {
        val moodsList = map(state.listOfMoods)
        setState(state.copy(listWithChosenMood = moodsList))
    }

    private fun map(emojiList: List<EmojiBody>): List<EmojiBodyUIModel> {
        return emojiList.map { model ->
            EmojiBodyUIModel(
                image = model.image,
                title = model.title,
                isChecked = model.isChecked,
                checkChanged = {
                    val list = state.listOfMoods.map {
                        if (it.title == state.chosenEmojiUIModel.title) it.copy(isChecked = !it.isChecked)
                        else it
                    }
                    setState(state.copy(listWithChosenMood = map(list)))
                    Log.d("-----checkChanged", list.toString())
                }
            )
        }
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

    private fun setState(newState: AddMoodViewState) {
        _addMoodStateLiveData.value = newState
    }
}
