package com.example.covidapp.model

import com.google.gson.annotations.SerializedName

data class CountryItem(

    @SerializedName("Country")
    val name: String,
    @SerializedName("ISO2")
    val shortCountryName: String,
    @SerializedName("Slug")
    val lowerCaseName: String
)