package com.example.moodtrackerproject.domain.map

interface Mapper<in E, T> {
    fun map(from: E): T
}