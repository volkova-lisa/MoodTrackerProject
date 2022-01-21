package com.example.moodtrackerproject.ui.notes.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.domain.NoteBody
import com.example.moodtrackerproject.ui.notes.Store
import timber.log.Timber

class NotesViewModel : ViewModel() {
    // TODO: should be val
    private val state: NotesViewState by lazy {
        Store.appState.notesState
    }

    // private lateinit var

    init {
        Store.appState.copy(
            notesState = NotesViewState(
                addNewNote = ::addNewNote,
                showFavourites = ::changeFavoriteStatus
            )
        )
    }

    private val _notesStateLiveData: MutableLiveData<NotesViewState> =
        MutableLiveData<NotesViewState>().apply {
            value = state
        }
    val liveData: LiveData<NotesViewState> get() = _notesStateLiveData

    private fun addNewNote() {
        setState(state.copy(action = NotesListAction.AddNewNote))
    }

    private fun changeFavoriteStatus() {
        val isFavoriteChecked = !state.isFavoriteChecked
        setState(
            state.copy(
                isFavoriteChecked = isFavoriteChecked,
                listOfNotes = map(
                    if (isFavoriteChecked) DataBaseRepository.getNotes().filter { it.isChecked }
                    else DataBaseRepository.getNotes()
                )
            )
        )
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
        _notesStateLiveData.value = state
    }
}
