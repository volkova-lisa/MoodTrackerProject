package com.example.moodtrackerproject.ui.mood.add

import com.example.moodtrackerproject.app.MviAction

data class AddMoodProps(
    val action: NewMoodAction? = null,
    val cancelAdding: () -> Unit = {},
    val fetchListOfMoods: () -> Unit = {},
    val saveMood: (Int, String) -> Unit,
    val moodItems: List<MoodItemProps> = listOf()
) {
    sealed class NewMoodAction : MviAction {
        object ShowMoodsScreen : NewMoodAction()
    }

    data class MoodItemProps(
        val image: Int = 0,
        val title: String = "",
        val isChecked: Boolean = false,
        val checkChanged: () -> Unit = {}
    ) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as MoodItemProps

            if (image != other.image) return false
            if (title != other.title) return false
            if (isChecked != other.isChecked) return false

            return true
        }

        override fun hashCode(): Int {
            var result = image
            result = 31 * result + title.hashCode()
            result = 31 * result + isChecked.hashCode()
            return result
        }
    }
}
