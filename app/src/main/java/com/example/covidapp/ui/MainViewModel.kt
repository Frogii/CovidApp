package com.example.covidapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covidapp.model.CountryItem
import com.example.covidapp.repository.CovidRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: CovidRepository) : ViewModel() {

    private val countriesLiveData: MutableLiveData<Response<List<CountryItem>>> = MutableLiveData()

    init {
        uploadAllCountries()
    }

    fun uploadAllCountries() {
        viewModelScope.launch {
            val response = repository.getAllCountries()
            if (response.isSuccessful) {
                countriesLiveData.postValue(response)
            }
        }
    }

    fun getCountriesLiveData(): MutableLiveData<Response<List<CountryItem>>> {
        return countriesLiveData
    }
}