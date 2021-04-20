package com.example.covidapp.api

import com.example.covidapp.model.CountryItem
import retrofit2.Response
import retrofit2.http.GET


interface CovidAPI {

    @GET("countries")
    suspend fun getCountries(): Response<List<CountryItem>>
}
