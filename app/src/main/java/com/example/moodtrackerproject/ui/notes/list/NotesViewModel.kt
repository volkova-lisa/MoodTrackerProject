package com.example.moodtrackerproject.ui.notes.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.domain.NoteBody
import com.example.moodtrackerproject.utils.PreferenceManager
import timber.log.Timber

class NotesViewModel : ViewModel() {

    private val notesList: MutableList<NoteBody>? = if (PreferenceManager.getNotes() == null) mutableListOf() else PreferenceManager.getNotes()

    val uiModels: List<NoteBodyUiModel> = DataBaseRepository.allNotes.value!!.map {
        map(it)
    }

    fun getAllNotes(): MutableLiveData<List<NoteBody>?> = MutableLiveData(notesList)
    fun getFavNotes(): MutableLiveData<MutableList<NoteBody>> = DataBaseRepository.favoriteNotes

    // map NoteBody to NoteBodyUiModel
    // fun getAllNotes
    // fun getFavNotes
    fun getUiNotes(): MutableLiveData<List<NoteBodyUiModel>> = MutableLiveData(uiModels)

    private fun map(model: NoteBody) = NoteBodyUiModel().also {
        it.noteId = System.currentTimeMillis().toString()
        it.date = model.date
        it.title = model.title
        it.text = model.text
        it.isChecked = false
        // TODO("change")
        it.checkChanged = { changed -> Timber.d(changed) }
        it.openDetails = { open -> Timber.d(open) }
    }
}
