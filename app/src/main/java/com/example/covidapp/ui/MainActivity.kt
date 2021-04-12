package com.example.covidapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidapp.adapter.CountriesRecAdapter
import com.example.covidapp.databinding.ActivityMainBinding
import com.example.covidapp.repository.CovidRepository

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var recAdapter: CountriesRecAdapter
    lateinit var activityMainBinding: ActivityMainBinding
    private var listOfCountries: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        setupRecycler()

        activityMainBinding.mainSpinnerView.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    Toast.makeText(view?.context, listOfCountries[position], Toast.LENGTH_SHORT).show()
                }

            }


        val repository = CovidRepository()
        val viewModelProviderFactory = MainViewModelProviderFactory(repository)
        mainViewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(MainViewModel::class.java)

        mainViewModel.getCountriesLiveData().observe(this) { response ->
            Log.d("myLog", response.body()?.size.toString())
            Log.d("myLog", response.body().toString())
            response.body()?.let { list ->
                recAdapter.setList(list)
                for (item in list) {
                    listOfCountries.add(item.name)
                }
                listOfCountries.sortBy { it }
                val arrayAdapter = ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_spinner_dropdown_item,
                    listOfCountries
                )
                activityMainBinding.mainSpinnerView.adapter = arrayAdapter
                activityMainBinding.mainSpinnerView.prompt = "Country"
            }

        }
    }

    private fun setupRecycler() {
        recAdapter = CountriesRecAdapter()
        activityMainBinding.mainRecyclerView.adapter = recAdapter
        activityMainBinding.mainRecyclerView.layoutManager =
            LinearLayoutManager(activityMainBinding.root.context)
    }
}