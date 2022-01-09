package com.example.moodtrackerproject.domain.map

interface NotesMapper<in E, T> {
    fun map(from: E): T
}
