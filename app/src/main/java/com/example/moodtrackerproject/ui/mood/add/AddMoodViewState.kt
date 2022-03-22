package com.example.moodtrackerproject.ui.mood.add

data class AddMoodViewState(
    val cancelAdding: () -> Unit = {},
    val saveMood: (Pair<Int, String>) -> Unit = {},
    val chosenEmojiUIModel: EmojiBodyUIModel = EmojiBodyUIModel(),
    val listWithChosenMood: List<EmojiBodyUIModel> = listOf()
)
