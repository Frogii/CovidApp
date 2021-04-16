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
import com.example.covidapp.utils.AppMapUtils
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    lateinit var map: GoogleMap
    lateinit var mainViewModel: MainViewModel
    lateinit var activityMainBinding: ActivityMainBinding
    private var listOfCountries: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        setAnimation()

        val mapBundle = savedInstanceState?.getBundle("MapBundle")
        activityMainBinding.mapView.onCreate(mapBundle)
        activityMainBinding.mapView.getMapAsync(this)
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
                    Toast.makeText(view?.context, listOfCountries[position], Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    private fun setAnimation() {
        val animRecovered =
            ObjectAnimator.ofFloat(activityMainBinding.imageViewInfectedRound, "alpha", 0f, 1f)
        animRecovered.duration = 1200
        animRecovered.repeatMode = ValueAnimator.REVERSE
        animRecovered.repeatCount = Animation.INFINITE
        animRecovered.start()
        val animDeaths =
            ObjectAnimator.ofFloat(activityMainBinding.imageViewDeathsRound, "alpha", 0f, 1f)
        animDeaths.duration = 1200
        animDeaths.repeatMode = ValueAnimator.REVERSE
        animDeaths.repeatCount = Animation.INFINITE
        animDeaths.start()
        val animInfected =
            ObjectAnimator.ofFloat(activityMainBinding.imageViewRecoveredRound, "alpha", 0f, 1f)
        animInfected.duration = 1200
        animInfected.repeatMode = ValueAnimator.REVERSE
        animInfected.repeatCount = Animation.INFINITE
        animInfected.start()
    }

    override fun onMapReady(p0: GoogleMap?) {
        p0?.let {
            map = it
        }
        map.uiSettings.isTiltGesturesEnabled = false
        map.uiSettings.isScrollGesturesEnabled = false
        map.uiSettings.isScrollGesturesEnabledDuringRotateOrZoom = false
        map.uiSettings.isZoomGesturesEnabled = false

        map.addMarker(AppMapUtils.setMarkerOptions(this, "Sydney", LatLng(53.893009, 27.567444)))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(53.893009, 27.567444), 3f ))
    }

    override fun onResume() {
        super.onResume()
        activityMainBinding.mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        activityMainBinding.mapView.onStart()
    }

    override fun onPause() {
        super.onPause()
        activityMainBinding.mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        activityMainBinding.mapView.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        activityMainBinding.mapView.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        activityMainBinding.mapView.onSaveInstanceState(outState)
    }
}