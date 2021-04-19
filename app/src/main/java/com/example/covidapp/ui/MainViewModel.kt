package com.example.covidapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covidapp.model.CountryItem
import com.example.covidapp.repository.CovidRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: CovidRepository) : ViewModel() {

    private val countriesLiveData: MutableLiveData<List<CountryItem>> = MutableLiveData()

    init {
        uploadAllCountries()
    }

    fun uploadAllCountries() {
        viewModelScope.launch {
            val listOfCountries = mutableListOf<CountryItem>()
            val response = repository.getAllCountries()
            if (response.isSuccessful) {
                response.body()?.let { list ->
                    for (item in list) {
                        listOfCountries.add(item)
                    }
                    listOfCountries.sortBy { country -> country.name }
                    countriesLiveData.postValue(listOfCountries)
                }
            }
        }
    }

    fun getCountriesLiveData(): MutableLiveData<List<CountryItem>> = countriesLiveData

}