package com.example.moodtrackerproject.ui.mood.add

import android.util.Log
import com.example.moodtrackerproject.app.AppState
import com.example.moodtrackerproject.app.MviAction
import com.example.moodtrackerproject.app.Store
import com.example.moodtrackerproject.app.mood.AddMoodState
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.domain.MoodModel
import com.example.moodtrackerproject.ui.BaseViewModel
import com.example.moodtrackerproject.ui.mood.add.AddMoodProps.NewMoodAction
import com.example.moodtrackerproject.utils.DateUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddMoodViewModel : BaseViewModel<AddMoodProps>() {

    init {
        setState(Store.appState.addMoodState)
    }

    override fun map(appState: AppState, action: MviAction?): AddMoodProps {
        val state = appState.addMoodState
        return AddMoodProps(
            action = action as? NewMoodAction,
            fetchListOfMoods = ::fetchListOfMoods,
            cancelAdding = {},
            saveMood = ::addNewMood,
            moodItems = state.moods.map {
                AddMoodProps.MoodItemProps(
                    image = it.image,
                    title = it.title,
                    isChecked = it.isChecked,
                    checkChanged = {
                        saveChosenEmoji(it.title)
                    }
                )
            },
        )
    }

    private fun fetchListOfMoods() {
        launch {
            val moodsList = withContext(Dispatchers.IO) {
                DataBaseRepository.getEmojiList()
            }
            setState(Store.appState.addMoodState.copy(moods = moodsList))
        }
    }

    private fun saveChosenEmoji(title: String) {
        launch {
            val moodsList = withContext(Dispatchers.IO) {
                DataBaseRepository.saveChosenEmoji(title)
            }
            setState(Store.appState.addMoodState.copy(moods = moodsList))
        }
    }

    private fun addNewMood(emojiSrc: Int, title: String) {
        launch {
            val mood = MoodModel(
                emojiSrc = emojiSrc,
                moodTitle = title,
                moodTime = DateUtils.getDateOfNote(),
            )
            Log.d("INSERT MOOD", emojiSrc.toString())
            DataBaseRepository.insertMood(mood)
            setState(
                Store.appState.addMoodState,
                action = NewMoodAction.ShowMoodsScreen
            )
        }
    }

    private fun setState(state: AddMoodState, action: NewMoodAction? = null) {
        setState(Store.appState.copy(addMoodState = state), action)
    }
}
