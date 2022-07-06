package com.example.moodtrackerproject.ui.mood.list

import com.example.moodtrackerproject.app.AppState
import com.example.moodtrackerproject.app.MviAction
import com.example.moodtrackerproject.app.Store
import com.example.moodtrackerproject.app.mood.MoodState
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.ui.BaseViewModel
import com.example.moodtrackerproject.ui.mood.list.MoodProps.MoodItemProps
import com.example.moodtrackerproject.ui.mood.list.MoodProps.MoodScreenActions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoodViewModel : BaseViewModel<MoodProps>() {

    init {
        setState(Store.appState.moodState)
    }

    override fun map(appState: AppState, action: MviAction?): MoodProps {
        val state = appState.moodState
        return MoodProps(
            action = action as? MoodScreenActions,
            listOfMoods = state.listOfMoods.map {
                MoodItemProps(
                    emojiSrc = it.emojiSrc,
                    moodTitle = it.moodTitle,
                    moodTime = it.moodTime,
                )
            },
            addNewMood = {
                setState(state, action = MoodScreenActions.StartAddMoodScreen)
            },
            openStressTestScreen = {
                setState(state, action = MoodScreenActions.StartStressTestScreen)
            },
            openAnxietyTestScreen = {
                setState(state, action = MoodScreenActions.StartStressTestScreen)
            },
            fetchListOfMoods = ::fetchListOfMoods,
            setTestType = ::setTestType
        )
    }

    private fun setTestType(type: Int) {
        val state = Store.appState.stressTestState
        if (type == 1) {
            Store.setState(
                state.copy(
                    questionList = DataBaseRepository.listOfAnxietyQs
                )
            )
        } else {
            Store.setState(
                state.copy(
                    questionList = DataBaseRepository.listOfStressQs
                )
            )
        }
    }

    private fun fetchListOfMoods() {
        launch {
            val moods = withContext(Dispatchers.IO) {
                DataBaseRepository.getMoods()
            }
            setState(Store.appState.moodState.copy(listOfMoods = moods))
        }
    }

    private fun setState(state: MoodState, action: MoodScreenActions? = null) {
        setState(Store.appState.copy(moodState = state), action)
    }
}
