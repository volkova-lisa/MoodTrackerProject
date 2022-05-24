package com.example.moodtrackerproject.app.mood

import com.example.moodtrackerproject.domain.EmojiModel

data class AddMoodState(
    val moods: List<EmojiModel> = emptyList(),
)
