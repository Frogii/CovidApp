package com.example.covidapp.utils

import com.example.covidapp.model.CountryCase

class AppCaseUtils {

    companion object {

        //Check provinces(if country have them), and sum cases for last day
        fun sumProvinceCases(list: List<CountryCase>): CountryCase {
            val filteredList = list.filter { it.date == list.last().date }
            return list.last().also { countryCase ->
                countryCase.confirmed = filteredList.sumBy { it.confirmed }
                countryCase.deaths = filteredList.sumBy { it.deaths }
                countryCase.recovered = filteredList.sumBy { it.recovered }
            }
        }
    }
}