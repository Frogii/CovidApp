package com.example.covidapp

import com.example.covidapp.utils.AppDateUtils
import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class AppDateUtilUnitTest {

    private fun getTodayDay(): String {
        return SimpleDateFormat("MMMM dd", Locale.getDefault()).format(Date())
    }

    @Test
    fun stringToDate_isCorrect1() {
        val date = AppDateUtils.convertDateToNewest("2021-04-20T00:00:00Z")
        assertEquals("April 20", date)
    }

    @Test
    fun stringToDate_isCorrect2() {
        val date = AppDateUtils.convertDateToNewest("3054-05-22T00:00:00Z")
        assertEquals("May 22", date)
    }

    @Test
    fun stringToDate_isCorrect3() {
        val date = AppDateUtils.convertDateToNewest("23.33.3213")
        assertEquals(getTodayDay(), date)
    }
}