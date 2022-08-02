package com.example.moodtrackerproject.data

import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.domain.*
import com.example.moodtrackerproject.utils.PreferenceManager

// single source of truth
object DataBaseRepository {

    private val listOfEmojis = listOf(
        EmojiModel(R.drawable.emoji_anger, "anger"),
        EmojiModel(R.drawable.emoji_awesome, "awesome"),
        EmojiModel(R.drawable.emoji_calm, "calm"),
        EmojiModel(R.drawable.emoji_concentrated, "concentrated"),
        EmojiModel(R.drawable.emoji_cool, "cool"),
        EmojiModel(R.drawable.emoji_crying, "crying"),
        EmojiModel(R.drawable.emoji_crying_inside, "crying inside"),
        EmojiModel(R.drawable.emoji_excited, "excited"),
        EmojiModel(R.drawable.emoji_furious, "furious"),
        EmojiModel(R.drawable.emoji_great, "great"),
        EmojiModel(R.drawable.emoji_puking, "puking"),
        EmojiModel(R.drawable.emoji_sad, "sad"),
        EmojiModel(R.drawable.emoji_shocked, "shocked"),
        EmojiModel(R.drawable.emoji_sick, "sick"),
        EmojiModel(R.drawable.emoji_sleepy, "sleepy"),
        EmojiModel(R.drawable.emoji_traumatised, "traumatised"),
    )

    val listOfStressQs = listOf(
        QuestionModel("Been upset because of something that happened unexpectedly?"),
        QuestionModel("Felt that you were unable to control important things in your life?"),
        QuestionModel("Felt nervous and 'stressed"),
        QuestionModel("Felt confident about your ability to handle your personal problems?"),
        QuestionModel("Felt that things were going your way?"),
        QuestionModel("Felt that things were going your way?")
    )
    val listOfAnxietyQs = listOf(
        QuestionModel("----------"),
        QuestionModel("----------"),
        QuestionModel("----------"),
        QuestionModel("----------"),
        QuestionModel("----------"),
        QuestionModel("----------")
    )

    private val lisOfOptions = listOf(
        OptionModel("Never", 1),
        OptionModel("Almost Never", 2),
        OptionModel("Sometimes", 3),
        OptionModel("Fairly Often", 4),
        OptionModel("Very Often", 5)
    )

    var stressPoints = 0
    var anxietyPoints = 0

    fun saveStressPoints(p: Int) {
        stressPoints += p
        saveTestResults(ResultsModel(stressPoints, anxietyPoints))
    }
    fun saveAnxietyPoints(p: Int) {
        anxietyPoints += p
        saveTestResults(ResultsModel(stressPoints, anxietyPoints))
    }

    fun getEmojiList() = listOfEmojis

    fun getOptions() = lisOfOptions

    fun saveChosenEmoji(title: String): List<EmojiModel> {
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

    fun saveSelected(title: String): List<OptionModel> {
        val list = lisOfOptions.map {
            if (it.text == title) it.copy(isChecked = !it.isChecked) else it
        }
        return list
    }

    fun getMoods() = PreferenceManager.getMoods()

    fun insertMood(moodModel: MoodModel) {

        val list = mutableListOf<MoodModel>().apply {
            addAll(getMoods())
            add(moodModel)
        }
        PreferenceManager.saveMoods(list)
    }

    fun saveHealth(healthModel: HealthModel) {
        PreferenceManager.saveHealth(healthModel)
    }

    fun getHealth() = PreferenceManager.getHealth()

    private fun saveTestResults(result: ResultsModel) {
        PreferenceManager.saveTests(result)
    }

    fun getTestResults() = PreferenceManager.getTests()

    fun saveLanguage(lang: String) {
        PreferenceManager.saveLanguage(lang)
    }

    fun saveMode(mode: Boolean) {
        PreferenceManager.saveLanguage(mode)
    }

    fun getLang() = PreferenceManager.getLanguage()
    fun getMode() = PreferenceManager.getMode()

    fun saveName(name: String) {
        PreferenceManager.saveName(name)
    }

    fun getName() = PreferenceManager.getName()

    fun saveEmail(email: String) {
        PreferenceManager.saveEmail(email)
    }

    fun getEmail(): String {
        return PreferenceManager.getEmail()
    }

    fun savePhoto(photo: String) {
        PreferenceManager.savePhoto(photo)
    }
    fun getPhoto() = PreferenceManager.getPhoto()

    fun saveHealthMax(maxHealthModel: MaxHealthModel) {
        PreferenceManager.saveHealthMax(maxHealthModel)
    }

    fun getHealthMax() = PreferenceManager.getHealthMax()
}
