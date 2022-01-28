package com.example.moodtrackerproject.ui.notes.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.domain.NoteBody
import com.example.moodtrackerproject.ui.notes.Store
import com.example.moodtrackerproject.utils.PreferenceManager
import timber.log.Timber

class NotesViewModel : ViewModel() {
    // TODO: should be val
    private var state: NotesViewState

    init {
        state = Store.appState.notesState.copy(
            addNewNote = ::addNewNote,
            showFavourites = ::changeFavoriteStatus,
            updateText = ::updateText
        )
        Store.setState(state)
    }

    private val _notesStateLiveData: MutableLiveData<NotesViewState> =
        MutableLiveData<NotesViewState>().apply {
            value = state
        }
    val liveData get() = _notesStateLiveData

    fun updateText() {
        // 0 - get new value
        // 1 - get from list  needed item
        // 2 - change its field
        // 3 - delete this firm pref
        // 4 - add new to prefs
        val newNote = mapBack(Store.appState.notesState.listOfNotes)
        val neededItemFromPref = PreferenceManager.getNotes().filter { it.noteId == state.currentId }
        Log.d("--------=========--", neededItemFromPref.toString())
        val newNeeded =
            if (neededItemFromPref.isNotEmpty()) {
                neededItemFromPref[0].copy(
                    title = Store.appState.notesState.listOfNotes[0].title,
                    text = Store.appState.notesState.listOfNotes[0].text
                )
            } else {
                NoteBody()
            }
        val newList = PreferenceManager.getNotes().drop(PreferenceManager.getNotes().indexOf(neededItemFromPref[0]))
        val newest = newList.toMutableList()
        newest.add(newNeeded)
        PreferenceManager.saveNotes(newest)
    }

    private fun addNewNote() {
        liveData.value = state.copy(action = NotesListAction.AddNewNote)
    }

    private fun changeFavoriteStatus() {
        val isFavoriteChecked = !Store.appState.notesState.isFavoriteChecked
        liveData.value =
            state.copy(
                isFavoriteChecked = isFavoriteChecked,
                listOfNotes = map(
                    if (isFavoriteChecked) DataBaseRepository.getNotes().filter { it.isChecked }
                    else DataBaseRepository.getNotes()
                )
            )

        Store.setState(liveData.value!!)
    }

    fun fetchListOfNotes() {
        val notes = map(DataBaseRepository.getNotes())
        setState(state.copy(listOfNotes = notes))
    }

    private fun map(notesList: List<NoteBody>): List<NoteBodyUiModel> {
        return notesList.map { model ->
            NoteBodyUiModel(
                noteId = model.noteId,
                date = model.date,
                title = model.title,
                text = model.text,
                isChecked = model.isChecked,
                isDeleted = model.isDeleted,
                checkChanged = {
                    val list = DataBaseRepository.setFavorite(it)
                    setState(state.copy(listOfNotes = map(list)))
                },
                openDetails = {
                    setState(state.copy(action = NotesListAction.StartDetailsScreen))
                    setState(
                        state.copy(
                            currentId = it.noteId,
                            listOfNotes = listOf(it)
                        )
                    )
                },
                deleteNote = { deleted ->
                    Timber.d(deleted)
                    // TODO: maybe combine these two functions because they are similar?
                    DataBaseRepository.setDeleted(model)
                    val list = DataBaseRepository.removeDeletedNotes()
                    setState(state.copy(listOfNotes = map(list)))
                },
            )
        }
    }
    private fun mapBack(notesList: List<NoteBodyUiModel>): List<NoteBody> {
        return notesList.map { model ->
            NoteBody(
                noteId = model.noteId,
                date = model.date,
                title = model.title,
                text = model.text,
                isChecked = model.isChecked,
                isDeleted = model.isDeleted
            )
        }
    }

    // TODO: need to think on some "unification"
    private fun setState(newState: NotesViewState) {
        Store.setState(newState)
        _notesStateLiveData.value = Store.appState.notesState
    }
}
