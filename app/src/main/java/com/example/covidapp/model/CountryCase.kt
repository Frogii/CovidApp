package com.example.covidapp.model

import com.google.gson.annotations.SerializedName

data class CountryCase(
    @SerializedName("City")
    val city: String,
    @SerializedName("Country")
    val country: String,
    @SerializedName("Confirmed")
    var confirmed: Int,
    @SerializedName("Deaths")
    var deaths: Int,
    @SerializedName("Recovered")
    var recovered: Int,
    @SerializedName("Date")
    val date: String,
    @SerializedName("Lat")
    val lat: String,
    @SerializedName("Lon")
    val lon: String
)