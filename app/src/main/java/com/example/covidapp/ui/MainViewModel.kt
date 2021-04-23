package com.example.covidapp.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covidapp.model.CountryCase
import com.example.covidapp.model.CountryItem
import com.example.covidapp.repository.CovidRepository
import com.example.covidapp.utils.Constants.NETWORK_ERROR
import com.example.covidapp.utils.Constants.NO_DATA
import com.example.covidapp.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel(private val repository: CovidRepository) : ViewModel() {

    private val countries: MutableLiveData<List<CountryItem>> = MutableLiveData()
    private val nameOfCountries: MutableLiveData<List<String>> = MutableLiveData()
    private val cases: MutableLiveData<Resource<CountryCase>> = MutableLiveData()

    init {
        uploadAllCountries()
    }

    fun getCases(countryName: String) {
        cases.postValue(Resource.Loading())
        viewModelScope.launch {
                try {
                    val response = repository.getCases(countryName)
                    if (response.isSuccessful) {
                        response.body()?.let { list ->
                            if (list.isNotEmpty()) {
                                cases.postValue(Resource.Success(list.last()))
                            } else {
                                cases.postValue(Resource.Error(NO_DATA))
                            }

                        }
                    }
                } catch (e: Exception) {
                    Log.d("myLog", e.message.toString())
                    cases.postValue(Resource.Error(NETWORK_ERROR))
                }
            }
    }

    private fun uploadAllCountries() {
        try {
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
                        listOfCountries.removeAt(0)

                        for (country in listOfCountries) {
                            listOfNames.add(country.name)
                        }
                        nameOfCountries.postValue(listOfNames)
                        countries.postValue(listOfCountries)
                    }
                }
            }
        } catch (e: Exception) {
            Log.d("myLog", e.message.toString())
        }
    }

    fun getCountries(): MutableLiveData<List<CountryItem>> = countries

    fun getNameOfCountries(): MutableLiveData<List<String>> = nameOfCountries

    fun getCase(): MutableLiveData<Resource<CountryCase>> = cases

}