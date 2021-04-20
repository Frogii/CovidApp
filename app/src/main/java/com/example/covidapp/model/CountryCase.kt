package com.example.covidapp.model

import com.google.gson.annotations.SerializedName

data class CountryCase(
    @SerializedName("City")
    val city: String = "",
    @SerializedName("Confirmed")
    val confirmed: Int = 0,
    @SerializedName("Country")
    val country: String = "",
    @SerializedName("Date")
    val date: String = "",
    @SerializedName("Deaths")
    val deaths: Int = 0,
    @SerializedName("Lat")
    val lat: String = "0",
    @SerializedName("Lon")
    val lon: String = "0",
    @SerializedName("Recovered")
    val recovered: Int = 0
)