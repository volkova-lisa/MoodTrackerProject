package com.example.moodtrackerproject.ui.notes.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.domain.NoteBody
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

class NotesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(NotesViewState())
    val uiState: StateFlow<NotesViewState> = _uiState.asStateFlow()

    private var fetchJob: Job? = null

    fun changeStarState() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.update {
                it.copy(isFavoriteChecked = !it.isFavoriteChecked)
            }
        }
    }

    fun fetchListOfNotes() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            val notes = map(DataBaseRepository.getNotes())
            _uiState.update {
                it.copy(listOfNotes = notes)
            }
        }
    }

    fun onToolbarStarClicked(checked: Boolean) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.update {
                it.copy(
                    listOfNotes = map(
                        if (checked) DataBaseRepository.getNotes().filter { it.isChecked }
                        else DataBaseRepository.getNotes()
                    )
                )
            }
        }
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
                    // with callback
//                    DataBaseRepository.setFavorite(it) {
//                        notesLiveData.value = map(it)
//                    }
                    // or without callback
                    val list = DataBaseRepository.setFavorite(it)
                    fetchJob?.cancel()
                    fetchJob = viewModelScope.launch {
                        _uiState.update {
                            it.copy(listOfNotes = map(list))
                        }
                    }

                    // Note: both implementations are not perfect and will be revised in the future
                },
                openDetails = { open -> Timber.d(open) },
                deleteNote = { deleted ->
                    Timber.d(deleted)
                    // TODO: maybe combine these two functions because they are similar?
                    DataBaseRepository.setDeleted(model)
                    val list = DataBaseRepository.removeDeletedNotes()
                    fetchJob?.cancel()
                    fetchJob = viewModelScope.launch {
                        _uiState.update {
                            it.copy(listOfNotes = map(list))
                        }
                    }
                },
            )
        }
    }
}
