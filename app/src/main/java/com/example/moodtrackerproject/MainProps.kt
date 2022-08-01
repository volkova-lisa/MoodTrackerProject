package com.example.moodtrackerproject

data class MainProps(
    val language: String = "",
    val photo: String = "",
    val name: String = "",
    val fetchData: () -> Unit = {}

)
