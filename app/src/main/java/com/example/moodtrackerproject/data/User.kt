package com.example.moodtrackerproject.data

data class User(val email: String, val name: String, val password: String) {
    override fun toString(): String {
        return "$email - $password - $name"
    }
}
