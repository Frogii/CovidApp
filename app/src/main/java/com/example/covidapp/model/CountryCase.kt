package com.example.covidapp.model

import com.google.gson.annotations.SerializedName

data class CountryCase(
    @SerializedName("City")
    val city: String,
    @SerializedName("Confirmed")
    val confirmed: Int,
    @SerializedName("Country")
    val country: String,
    @SerializedName("Date")
    val date: String,
    @SerializedName("Deaths")
    val deaths: Int,
    @SerializedName("Lat")
    val lat: String,
    @SerializedName("Lon")
    val lon: String,
    @SerializedName("Recovered")
    val recovered: Int
)