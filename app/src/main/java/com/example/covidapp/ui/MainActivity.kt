package com.example.covidapp.ui

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.covidapp.R
import com.example.covidapp.adapter.ArraySpinnerAdapter
import com.example.covidapp.adapter.CountriesRecAdapter
import com.example.covidapp.databinding.ActivityMainBinding
import com.example.covidapp.repository.CovidRepository

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var activityMainBinding: ActivityMainBinding
    private var listOfCountries: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        setAnimation()
        val repository = CovidRepository()
        val viewModelProviderFactory = MainViewModelProviderFactory(repository)
        mainViewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(MainViewModel::class.java)

        mainViewModel.getCountriesLiveData().observe(this) { response ->
            Log.d("myLog", response.body()?.size.toString())
            Log.d("myLog", response.body().toString())
            response.body()?.let { list ->
                for (item in list) {
                    listOfCountries.add(item.name)
                }
                listOfCountries.sortBy { it }
                val arrayAdapter = ArraySpinnerAdapter(this, R.layout.spinner_item, listOfCountries)

                activityMainBinding.mainSpinnerView.adapter = arrayAdapter
            }
        }

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
    }

    private fun setAnimation() {
        val animRecovered = ObjectAnimator.ofFloat(activityMainBinding.imageViewInfectedRound, "alpha", 0f, 1f)
        animRecovered.duration = 1200
        animRecovered.repeatMode = ValueAnimator.REVERSE
        animRecovered.repeatCount = Animation.INFINITE
        animRecovered.start()
        val animDeaths = ObjectAnimator.ofFloat(activityMainBinding.imageViewDeathsRound, "alpha", 0f, 1f)
        animDeaths.duration = 1200
        animDeaths.repeatMode = ValueAnimator.REVERSE
        animDeaths.repeatCount = Animation.INFINITE
        animDeaths.start()
        val animInfected = ObjectAnimator.ofFloat(activityMainBinding.imageViewRecoveredRound, "alpha", 0f, 1f)
        animInfected.duration = 1200
        animInfected.repeatMode = ValueAnimator.REVERSE
        animInfected.repeatCount = Animation.INFINITE
        animInfected.start()
    }
}