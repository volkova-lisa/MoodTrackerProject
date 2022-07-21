package com.example.moodtrackerproject.app.tests

import com.example.moodtrackerproject.domain.ResultsModel

data class TestResultsState(
    val testType: Int = 0,
    val resultsModel: ResultsModel? = null
)
