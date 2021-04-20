package com.example.covidapp.api

import com.example.covidapp.model.CountryCase
import com.example.covidapp.model.CountryItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface CovidAPI {

    @GET("countries")
    suspend fun getCountries(): Response<List<CountryItem>>

    @GET("live/country/{countryName}")
    suspend fun getCases(@Path("countryName") countryName: String): Response<List<CountryCase>>
}
