package com.example.moodtrackerproject.app

import com.example.moodtrackerproject.app.notes.NoteDetailsState
import com.example.moodtrackerproject.app.notes.NotesState
import com.example.moodtrackerproject.ui.mood.add.AddMoodViewState
import com.example.moodtrackerproject.ui.mood.list.MoodViewState
import com.example.moodtrackerproject.ui.mood.tests.StressTestState

data class AppState(
    // FIXME: login/registration/reset_password must also have states, but we'll skip them for simplicity
    val notesState: NotesState = NotesState(),
    val noteDetailsState: NoteDetailsState = NoteDetailsState(),
    val addMoodState: AddMoodViewState = AddMoodViewState(),
    val moodState: MoodViewState = MoodViewState(),
    val stressTestState: StressTestState = StressTestState()
)
