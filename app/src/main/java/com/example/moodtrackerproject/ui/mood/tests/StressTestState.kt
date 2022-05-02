package com.example.moodtrackerproject.ui.mood.tests

import com.example.moodtrackerproject.data.DataBaseRepository

data class StressTestState(
    val chosenAnswer: OptionUiModel = OptionUiModel(),
    val question: QuestionBody = DataBaseRepository.listOfStressQs[0],
    val listOfOptions: List<OptionUiModel> = listOf(),
    val points: Int = 0,
    val setQuestion: (Int) -> Unit = {},
    val currQuestionNum: Int = 0,
    val moveQuestion: (Int) -> Unit = {},
)
