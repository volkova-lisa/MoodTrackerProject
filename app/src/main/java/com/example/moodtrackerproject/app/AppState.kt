package com.example.moodtrackerproject.app

import com.example.moodtrackerproject.app.mood.AddMoodState
import com.example.moodtrackerproject.app.mood.MoodState
import com.example.moodtrackerproject.app.notes.NoteDetailsState
import com.example.moodtrackerproject.app.notes.NotesState
import com.example.moodtrackerproject.app.tests.StressTestState
import com.example.moodtrackerproject.app.tests.TestResultsState

data class AppState(
    // FIXME: login/registration/reset_password must also have states, but we'll skip them for simplicity
    val notesState: NotesState = NotesState(),
    val noteDetailsState: NoteDetailsState = NoteDetailsState(),
    val moodState: MoodState = MoodState(),
    val addMoodState: AddMoodState = AddMoodState(),
    val stressTestState: StressTestState = StressTestState(),
    val testResultsState: TestResultsState = TestResultsState(),
    val homeState: HomeState = HomeState()
)
