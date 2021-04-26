package com.example.covidapp.model

import com.google.gson.annotations.SerializedName

data class CountryCase(
    @SerializedName("City")
    val city: String,
    @SerializedName("Confirmed")
    var confirmed: Int,
    @SerializedName("Country")
    val country: String,
    @SerializedName("Date")
    val date: String,
    @SerializedName("Deaths")
    var deaths: Int,
    @SerializedName("Lat")
    val lat: String,
    @SerializedName("Lon")
    val lon: String,
    @SerializedName("Recovered")
    var recovered: Int
)