package com.example.moodtrackerproject.ui.mood.add

data class AddMoodViewState(
    val action: NewMoodAction? = null,
    val cancelAdding: () -> Unit = {},
    val saveMood: (Pair<Int, String>) -> Unit = {},
    val chosenEmojiUIModel: EmojiBodyUIModel = EmojiBodyUIModel(),
    val listWithChosenMood: List<EmojiBodyUIModel> = listOf()
)

sealed class NewMoodAction {
    object ShowMoodsScreen : NewMoodAction()
}