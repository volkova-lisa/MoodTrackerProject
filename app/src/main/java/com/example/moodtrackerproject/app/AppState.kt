package com.example.moodtrackerproject.app

import com.example.moodtrackerproject.app.mood.MoodState
import com.example.moodtrackerproject.app.notes.NoteDetailsState
import com.example.moodtrackerproject.app.notes.NotesState
import com.example.moodtrackerproject.ui.mood.add.AddMoodViewState
import com.example.moodtrackerproject.ui.mood.tests.StressTestState

data class AppState(
    // FIXME: login/registration/reset_password must also have states, but we'll skip them for simplicity
    val notesState: NotesState = NotesState(),
    val noteDetailsState: NoteDetailsState = NoteDetailsState(),
    val moodState: MoodState = MoodState(),
    val addMoodState: AddMoodViewState = AddMoodViewState(),
    val stressTestState: StressTestState = StressTestState()
)
