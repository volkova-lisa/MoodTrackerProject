package com.example.moodtrackerproject.ui.mood.add

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
                EmojiBody(R.drawable.emoji_anger), EmojiBody(R.drawable.emoji_awesome),
                EmojiBody(R.drawable.emoji_calm), EmojiBody(R.drawable.emoji_concentrated),
                EmojiBody(R.drawable.emoji_cool), EmojiBody(R.drawable.emoji_crying),
                EmojiBody(R.drawable.emoji_crying_inside), EmojiBody(R.drawable.emoji_excited),
                EmojiBody(R.drawable.emoji_furious), EmojiBody(R.drawable.emoji_great),
                EmojiBody(R.drawable.emoji_puking), EmojiBody(R.drawable.emoji_sad),
                EmojiBody(R.drawable.emoji_shocked), EmojiBody(R.drawable.emoji_sick),
                EmojiBody(R.drawable.emoji_sleepy), EmojiBody(R.drawable.emoji_traumatised),
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
                emojiChecked = {
                    setState(state.copy(chosenEmojiUIModel = it))
                    val list = state.listOfMoods
                    setState(state.copy(listWithChosenMood = map(list)))
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
