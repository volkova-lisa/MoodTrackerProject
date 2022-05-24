package com.example.moodtrackerproject.data

import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.domain.NoteModel
import com.example.moodtrackerproject.ui.mood.add.EmojiBody
import com.example.moodtrackerproject.ui.mood.list.MoodBody
import com.example.moodtrackerproject.ui.mood.tests.OptionBody
import com.example.moodtrackerproject.ui.mood.tests.QuestionBody
import com.example.moodtrackerproject.utils.PreferenceManager

// single source of truth
object DataBaseRepository {

    private val listOfEmojis = listOf(
        EmojiBody(R.drawable.emoji_anger, "anger"),
        EmojiBody(R.drawable.emoji_awesome, "awesome"),
        EmojiBody(R.drawable.emoji_calm, "calm"),
        EmojiBody(R.drawable.emoji_concentrated, "concentrated"),
        EmojiBody(R.drawable.emoji_cool, "cool"),
        EmojiBody(R.drawable.emoji_crying, "crying"),
        EmojiBody(R.drawable.emoji_crying_inside, "crying inside"),
        EmojiBody(R.drawable.emoji_excited, "excited"),
        EmojiBody(R.drawable.emoji_furious, "furious"),
        EmojiBody(R.drawable.emoji_great, "great"),
        EmojiBody(R.drawable.emoji_puking, "puking"),
        EmojiBody(R.drawable.emoji_sad, "sad"),
        EmojiBody(R.drawable.emoji_shocked, "shocked"),
        EmojiBody(R.drawable.emoji_sick, "sick"),
        EmojiBody(R.drawable.emoji_sleepy, "sleepy"),
        EmojiBody(R.drawable.emoji_traumatised, "traumatised"),
    )

    val listOfStressQs = listOf(
        QuestionBody("Been upset because of something that happened unexpectedly?"),
        QuestionBody("Felt that you were unable to control important things in your life?"),
        QuestionBody("Felt nervous and 'stressed"),
        QuestionBody("Felt confident about your ability to handle your personal problems?"),
        QuestionBody("Felt that things were going your way?"),
        QuestionBody("Felt that things were going your way?")
    )

    private val lisOfOptions = listOf(
        OptionBody("Never", 1),
        OptionBody("Almost Never", 2),
        OptionBody("Sometimes", 3),
        OptionBody("Fairly Often", 4),
        OptionBody("Very Often", 5)
    )

    var stressPoints = 0
    fun savePoints(p: Int) {
        stressPoints = stressPoints + p
    }

    fun getEmojiList() = listOfEmojis

    fun getOptions() = lisOfOptions

    fun saveChosenEmoji(title: String): List<EmojiBody> {
        val list = getEmojiList().map {
            if (it.title == title) it.copy(isChecked = !it.isChecked) else it
        }
        return list
    }

    fun getNotes() = PreferenceManager.getNotes()

    fun insertNote(noteBody: NoteModel) {
        val list = mutableListOf<NoteModel>().apply {
            addAll(getNotes())
            add(noteBody)
        }
        saveNotes(list)
    }

    // TODO("ask before delete")
    fun setNoteDeleted(noteBody: NoteModel) {
        val list = getNotes().map {
            if (it.noteId == noteBody.noteId) it.copy(isDeleted = !it.isDeleted) else it
        }
        saveNotes(list)
    }

    fun removeDeletedNotes(): List<NoteModel> {
        val list = getNotes().filter { !it.isDeleted }
        saveNotes(list)
        return list
    }

    fun saveNotes(notesList: List<NoteModel>) {
        PreferenceManager.saveNotes(notesList)
    }

    fun saveFavorite(noteId: String): List<NoteModel> {
        val list = getNotes().map {
            if (it.noteId == noteId) it.copy(isChecked = !it.isChecked) else it
        }
        saveNotes(list)
        return list
    }

    fun saveSelected(title: String): List<OptionBody> {
        val list = lisOfOptions.map {
            if (it.text == title) it.copy(isChecked = !it.isChecked) else it
        }
        return list
    }

    fun getMoods() = PreferenceManager.getMoods()

    fun saveMoods(moodsList: List<MoodBody>) {
        PreferenceManager.saveMoods(moodsList)
    }

    fun insertMood(moodBody: MoodBody, onSuccess: () -> Unit) {
        val list = mutableListOf<MoodBody>().apply {
            addAll(getMoods())
            add(moodBody)
        }
        saveMoods(list)
        onSuccess()
    }
}
