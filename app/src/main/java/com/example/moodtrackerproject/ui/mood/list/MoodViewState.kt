package com.example.moodtrackerproject.ui.mood.list

data class MoodViewState(
    val action: MoodScreenActions? = null,
    val listOfMoods: List<MoodBody> = listOf(),
    val addNewMood: () -> Unit = {}
)

sealed class MoodScreenActions {
    object StartAddMoodScreen : MoodScreenActions()
}
