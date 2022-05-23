package com.example.moodtrackerproject.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun getDateOfNote(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("MMM", Locale.getDefault())
        val month = dateFormat.format(calendar.get(Calendar.MONTH))
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        return "$month $day, $hour:$minute"
    }
}
