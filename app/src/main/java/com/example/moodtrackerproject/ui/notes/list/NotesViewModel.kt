package com.example.moodtrackerproject.ui.notes.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.domain.NoteBody
import com.example.moodtrackerproject.ui.notes.Store
import timber.log.Timber

class NotesViewModel : ViewModel() {
    // TODO: should be val
    private var state: NotesViewState
    init {
        state = Store.appState.notesState.copy(
            addNewNote = ::addNewNote,
            showFavourites = ::changeFavoriteStatus
        )
        Store.setState(state)
    }
//    by lazy {
//        Store.appState.notesState
//    }

    private val _notesStateLiveData: MutableLiveData<NotesViewState> =
        MutableLiveData<NotesViewState>().apply {
            value = state
        }
    val liveData get() = _notesStateLiveData

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
                    setState(state.copy(currentId = it))
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

    // TODO: need to think on some "unification"
    private fun setState(newState: NotesViewState) {
        Store.setState(newState)
        _notesStateLiveData.value = Store.appState.notesState
    }
}
