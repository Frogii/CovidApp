package com.example.covidapp.ui

import android.os.Bundle
import android.util.Log
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        setupRecycler()

        val repository = CovidRepository()
        val viewModelProviderFactory = MainViewModelProviderFactory(repository)
        mainViewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(MainViewModel::class.java)

        mainViewModel.getCountriesLiveData().observe(this) { response ->
            Log.d("myLog", response.body()?.size.toString())
            Log.d("myLog", response.body().toString())
            response.body()?.let { list -> recAdapter.setList(list) }
        }
    }

    private fun setupRecycler() {
        recAdapter = CountriesRecAdapter()
        activityMainBinding.mainRecyclerView.adapter = recAdapter
        activityMainBinding.mainRecyclerView.layoutManager =
            LinearLayoutManager(activityMainBinding.root.context)
    }
}