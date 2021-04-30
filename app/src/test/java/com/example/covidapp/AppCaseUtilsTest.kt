package com.example.covidapp

import com.example.covidapp.model.CountryCase
import com.example.covidapp.utils.AppCaseUtils
import org.junit.Assert.assertEquals
import org.junit.Test

class AppCaseUtilsTest {

    private val testCountry1 = CountryCase("a", "A", 4, 2, 5, "21.01.2021", "0", "0")
    private val testCountry2 = CountryCase("a", "A", 8, 4, 10, "21.01.2021", "0", "0")
    private val testCountry3 = CountryCase("a", "A", 20, 10, 25, "21.01.2021", "0", "0")

    private val testResponse1 = arrayListOf(
        CountryCase("a", "A", 4, 2, 5, "10.10.2000", "0", "0"),
        CountryCase("a", "A", 4, 2, 5, "10.10.2000", "0", "0"),
        CountryCase("a", "A", 4, 2, 5, "10.10.2000", "0", "0"),
        CountryCase("a", "A", 4, 2, 5, "10.10.2000", "0", "0"),
        CountryCase("a", "A", 4, 2, 5, "21.01.2021", "0", "0")
    )

    private val testResponse2 = arrayListOf(
        CountryCase("a", "A", 4, 2, 5, "10.10.2000", "0", "0"),
        CountryCase("a", "A", 4, 2, 5, "10.10.2000", "0", "0"),
        CountryCase("a", "A", 4, 2, 5, "10.10.2000", "0", "0"),
        CountryCase("a", "A", 4, 2, 5, "21.01.2021", "0", "0"),
        CountryCase("a", "A", 4, 2, 5, "21.01.2021", "0", "0")
    )

    private val testResponse3 = arrayListOf(
        CountryCase("a", "A", 4, 2, 5, "21.01.2021", "0", "0"),
        CountryCase("a", "A", 4, 2, 5, "21.01.2021", "0", "0"),
        CountryCase("a", "A", 4, 2, 5, "21.01.2021", "0", "0"),
        CountryCase("a", "A", 4, 2, 5, "21.01.2021", "0", "0"),
        CountryCase("a", "A", 4, 2, 5, "21.01.2021", "0", "0")
    )

    @Test
    fun sumProvinceTest1() {
        assertEquals(testCountry1, AppCaseUtils.sumProvinceCases(testResponse1))
    }

    @Test
    fun sumProvinceTest2() {
        assertEquals(testCountry2, AppCaseUtils.sumProvinceCases(testResponse2))
    }

    @Test
    fun sumProvinceTest3() {
        assertEquals(testCountry3, AppCaseUtils.sumProvinceCases(testResponse3))
    }
}