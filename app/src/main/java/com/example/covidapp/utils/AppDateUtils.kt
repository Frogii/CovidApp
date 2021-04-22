package com.example.covidapp.utils

import java.text.SimpleDateFormat
import java.util.*

class AppDateUtils {

    companion object {

        private const val apiDatePattern = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        private const val newestUpdatePattern = "MMMM dd"
        private var dateFormatter = SimpleDateFormat(apiDatePattern, Locale.getDefault())

        fun convertDateToNewest(dateString: String): String{
            return dateToString(stringToDate(dateString))
        }

        private fun stringToDate(dateString: String): Date {
            return dateFormatter.parse(dateString) ?: Date()
        }

        private  fun dateToString(date: Date): String {
            return SimpleDateFormat(newestUpdatePattern, Locale.getDefault()).format(date)
        }

    }
}