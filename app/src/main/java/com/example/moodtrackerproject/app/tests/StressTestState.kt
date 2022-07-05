package com.example.moodtrackerproject.app.tests

import com.example.moodtrackerproject.domain.OptionModel
import com.example.moodtrackerproject.domain.QuestionModel

data class StressTestState(
    val question: QuestionModel = QuestionModel(),
    val listOfOptions: List<OptionModel> = listOf(),
    val points: Int = 0,
    val currQuestionNum: Int = 0,
    val testType: Int = 0,
    val questionList: List<QuestionModel> = listOf()
)
