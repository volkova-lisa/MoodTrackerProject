package com.example.moodtrackerproject.ui.mood.tests

import com.example.moodtrackerproject.app.MviAction

data class StressTestProps(
    val questionText: String,
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
    ) {
//        override fun equals(other: Any?): Boolean {
//            if (this === other) return true
//            if (javaClass != other?.javaClass) return false
//
//            other as OptionItemProps
//
//            if (text != other.text) return false
//            if (points != other.points) return false
//            if (isChecked != other.isChecked) return false
//
//            return true
//        }
//
//        override fun hashCode(): Int {
//            var result = text.hashCode()
//            result = 31 * result + points
//            result = 31 * result + isChecked.hashCode()
//            return result
//        }
    }
}
