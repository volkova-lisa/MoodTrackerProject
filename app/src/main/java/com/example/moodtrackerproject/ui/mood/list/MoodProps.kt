package com.example.moodtrackerproject.ui.mood.list

import com.example.moodtrackerproject.app.MviAction

data class MoodProps(
    val action: MoodScreenActions? = null,
    val testType: Int = 0,
    val listOfMoods: List<MoodItemProps> = listOf(),
    val addNewMood: () -> Unit = {},
    val fetchListOfMoods: () -> Unit = {},
    val openStressTestScreen: () -> Unit = {},
    val openAnxietyTestScreen: () -> Unit = {},
    val setTestType: (Int) -> Unit = {},
) {
    sealed class MoodScreenActions : MviAction {
        object StartAddMoodScreen : MoodScreenActions()
        object StartStressTestScreen : MoodScreenActions()
    }

    data class MoodItemProps(
        val emojiSrc: Int = 0,
        val moodTitle: String = "",
        val moodTime: String = " "
    ) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as MoodItemProps

            if (emojiSrc != other.emojiSrc) return false
            if (moodTitle != other.moodTitle) return false
            if (moodTime != other.moodTime) return false

            return true
        }

        override fun hashCode(): Int {
            var result = emojiSrc
            result = 31 * result + moodTitle.hashCode()
            result = 31 * result + moodTime.hashCode()
            return result
        }
    }
}
