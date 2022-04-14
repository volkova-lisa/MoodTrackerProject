package com.example.moodtrackerproject.ui.mood.tests

data class StressTestState(
    val chosenAnswer: OptionUiModel = OptionUiModel(),
    val question: QuestionBody = QuestionBody(),
    val listOfOptions: List<OptionUiModel> = listOf(),
    val points: Int = 0,
    val setQuestion: (Int) -> Unit = {}
)
