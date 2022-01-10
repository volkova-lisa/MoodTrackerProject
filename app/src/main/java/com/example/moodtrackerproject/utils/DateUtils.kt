package com.example.moodtrackerproject.utils

import android.icu.text.SimpleDateFormat
import java.util.*

class DateUtils {
    private val calendar: Calendar = Calendar.getInstance()
    private val month = SimpleDateFormat("MMM").format(calendar.get(Calendar.MONTH))
    private val day = calendar.get(Calendar.DAY_OF_MONTH)
    private val hour = calendar.get(Calendar.HOUR_OF_DAY)
    private val minute = calendar.get(Calendar.MINUTE)
    private val noteDate = "$month $day, $hour:$minute"

    fun getDateOfNote(): String {
        return noteDate
    }
}
