package com.example.covidapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covidapp.model.CountryItem
import com.example.covidapp.repository.CovidRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: CovidRepository) : ViewModel() {

    private val countries: MutableLiveData<List<CountryItem>> = MutableLiveData()
    private val nameOfCountries: MutableLiveData<List<String>> = MutableLiveData()

    init {
        uploadAllCountries()
    }

    fun uploadAllCountries() {
        viewModelScope.launch {
            val listOfCountries = mutableListOf<CountryItem>()
            val listOfNames = mutableListOf<String>()
            val response = repository.getAllCountries()
            if (response.isSuccessful) {
                response.body()?.let { list ->
                    for (item in list) {
                        listOfCountries.add(item)
                    }
                    listOfCountries.sortBy { country -> country.name }
                    for (country in listOfCountries) {
                        listOfNames.add(country.name)
                    }
                    nameOfCountries.postValue(listOfNames)
                    countries.postValue(listOfCountries)
                }
            }
        }
    }

    fun getCountries(): MutableLiveData<List<CountryItem>> = countries

    fun getNameOfCountries(): MutableLiveData<List<String>> = nameOfCountries

}