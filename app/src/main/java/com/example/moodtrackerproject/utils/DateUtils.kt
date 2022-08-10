package com.example.moodtrackerproject.utils

import java.util.*

object DateUtils {
    fun getDateOfNote(): String {
        val currDate = Calendar.getInstance().time.formatTo(dateFormat = "MMM dd, hh:mm")
        return "$currDate"
    }
}
