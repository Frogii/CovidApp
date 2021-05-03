package com.example.covidapp.utils

import java.text.SimpleDateFormat
import java.util.*

class AppDateUtils {

    companion object {

        private const val apiDatePattern = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        private const val newestUpdatePatternDefault = "MMMM dd"
        private const val newestUpdatePatternRU = "dd MMMM"
        private const val localeRU = "ru"
        private var dateFormatter = SimpleDateFormat(apiDatePattern, Locale.getDefault())

        fun convertDateToNewest(dateString: String): String {
            return dateToString(stringToDate(dateString))
        }

        private fun stringToDate(dateString: String): Date {
            return try {
                dateFormatter.parse(dateString) as Date
            } catch (e: Exception) {
                Date()
            }
        }

        private fun dateToString(date: Date): String {
            val pattern: String = if (Locale.getDefault().language == localeRU) {
                newestUpdatePatternRU
            } else {
                newestUpdatePatternDefault
            }
            return SimpleDateFormat(pattern, Locale.getDefault()).format(date)
        }

    }
}