package com.example.moodtrackerproject.ui.mood.tests.anger

import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.ui.mood.tests.OptionUiModel
import com.example.moodtrackerproject.ui.mood.tests.QuestionBody

data class HappinesTestState(
    val chosenAnswer: OptionUiModel = OptionUiModel(),
    val question: QuestionBody = DataBaseRepository.listOfStressQs[0],
    val listOfOptions: List<OptionUiModel> = listOf(),
    val points: Int = 0,
    val setQuestion: () -> Unit = {},
    val currQuestionNum: Int = 0,
    val moveQuestion: () -> Unit = {},
    val again: () -> Unit = {},
)
