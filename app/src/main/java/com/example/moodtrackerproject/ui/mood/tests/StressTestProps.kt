package com.example.moodtrackerproject.ui.mood.tests

import com.example.moodtrackerproject.app.MviAction
import com.example.moodtrackerproject.domain.QuestionModel

data class StressTestProps(
    val questionList: List<QuestionModel> = listOf(),
    val listOfOptions: List<OptionItemProps> = listOf(),
    val stressQuestionsQty: Int,
    val points: Int = 0,
    val fetchListOfOptions: () -> Unit = {},
    val setQuestion: () -> Unit = {},
    val currQuestionNum: Int = 0,
    val moveQuestion: () -> Unit = {},
    val again: () -> Unit = {},
    val openMood: () -> Unit = {},
    val openResults: () -> Unit = {},
    val savePoints: (Int) -> Unit = {},
    val action: StressTestActions? = null,
    val curTestType: Int = 0,
    val shareTestType: (Int) -> Unit = {}
) {

    sealed class StressTestActions : MviAction {
        object OpenResults : StressTestActions()
        object OpenMood : StressTestActions()
    }

    data class OptionItemProps(
        val text: String = "",
        val points: Int = 0,
        val isChecked: Boolean = false,
        val checkChanged: () -> Unit,
    )
}
