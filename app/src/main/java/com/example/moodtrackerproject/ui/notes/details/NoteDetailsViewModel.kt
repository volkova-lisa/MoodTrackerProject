package com.example.moodtrackerproject.ui.notes.details

import com.example.moodtrackerproject.app.Store
import com.example.moodtrackerproject.app.notes.NoteDetailsState
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.ui.BaseViewModel
import com.example.moodtrackerproject.ui.notes.details.NoteDetailsProps.DetailsAction
import com.example.moodtrackerproject.utils.DateUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteDetailsViewModel : BaseViewModel<NoteDetailsProps>() {

    init {
        setState(Store.appState.noteDetailsState)
    }

    private fun map(state: NoteDetailsState, action: DetailsAction?): NoteDetailsProps {
        return NoteDetailsProps(
            action = action,
            editClicked = {},
            changeEditVisibility = {
                setState(state.copy(isEditNoteVisible = !state.isEditNoteVisible))
            },
            backClicked = {
                setState(state, action = DetailsAction.ShowAllNotes)
            },
            cancelClicked = {
                setState(state, action = DetailsAction.CancelEditing)
            },
            setNote = ::setNote,
            saveEdited = ::saveEditedInStore,
            noteId = state.noteModel.noteId,
            date = state.noteModel.date,
            editedDate = state.noteModel.editDate,
            title = state.noteModel.title,
            text = state.noteModel.text,
            isEditNoteVisible = state.isEditNoteVisible,
        )
    }

    private fun setNote() {
        val notesState = Store.appState.notesState
        val note = notesState.listOfNotes.find { it.noteId == notesState.currentId }
        if (note != null) {
            setState(
                Store.appState.noteDetailsState.copy(
                    noteModel = note,
                )
            )
        }
    }

    private fun saveEditedInStore(title: String, text: String) {
        launch {
            val notesState = Store.appState.notesState
            val detailsState = Store.appState.noteDetailsState
            withContext(Dispatchers.Default) {
                val listOfNotes = notesState.listOfNotes.map {
                    if (it.noteId == detailsState.noteModel.noteId) {
                        it.copy(
                            title = title,
                            text = text,
                            editDate = DateUtils.getDateOfNote(),
                        )
                    } else it
                }
                Store.setState(notesState.copy(listOfNotes = listOfNotes))
                DataBaseRepository.saveNotes(listOfNotes)
            }
            setState(
                detailsState.copy(
                    isEditNoteVisible = false,
                    noteModel = detailsState.noteModel.copy(
                        title = title,
                        text = text,
                        editDate = DateUtils.getDateOfNote(),
                    )
                ),
            )
        }
    }

    private fun setState(state: NoteDetailsState, action: DetailsAction? = null) {
        Store.setState(state)
        liveData.value = map(state, action)
    }
}
