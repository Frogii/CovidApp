package com.example.covidapp.repository

import com.example.covidapp.api.RetrofitInstance
import com.example.covidapp.model.CountryCase
import com.example.covidapp.model.CountryItem
import retrofit2.Response

class CovidRepository {

    private val api = RetrofitInstance.api

    suspend fun getAllCountries(): Response<List<CountryItem>> {
        return api.getCountries()
    }

    suspend fun getCases(countryName: String): Response<List<CountryCase>> {
        return api.getCases(countryName)
    }
}