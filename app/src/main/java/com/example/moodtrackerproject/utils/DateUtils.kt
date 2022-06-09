package com.example.moodtrackerproject.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    @SuppressLint("SimpleDateFormat")
    fun getDateOfNote(): String {
        val calendar: Calendar = Calendar.getInstance()
        val month = SimpleDateFormat("MMM").format(calendar.get(Calendar.MONTH))
//        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val noteDate = "$month $day, $hour:$minute"
        return noteDate
    }

    fun getToday(): String {
        val sdf = SimpleDateFormat("dd MMM")
        val currentDate = sdf.format(Date())
        return "Today is $currentDate"
    }
}
