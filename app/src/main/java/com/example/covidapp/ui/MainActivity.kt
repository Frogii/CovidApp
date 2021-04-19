package com.example.covidapp.ui

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.covidapp.R
import com.example.covidapp.adapter.ArraySpinnerAdapter
import com.example.covidapp.databinding.ActivityMainBinding
import com.example.covidapp.repository.CovidRepository
import com.example.covidapp.utils.AppMapUtils
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng

class MainActivity : MapActivity(), OnMapReadyCallback {

    lateinit var map: GoogleMap
    lateinit var mainViewModel: MainViewModel
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
        val arrayAdapter = ArraySpinnerAdapter(this, R.layout.spinner_item, listOfCountries)
        activityMainBinding.mainSpinnerView.adapter = arrayAdapter

        mainViewModel.getCountriesLiveData().observe(this) { list ->
            for (country in list) {
                listOfCountries.add(country.name)
            }
            arrayAdapter.setList(listOfCountries)

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
                    if (view != null) {
                        Toast.makeText(view.context, listOfCountries[position], Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

        activityMainBinding.imageViewMenu.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }

    override fun onMapReady(p0: GoogleMap?) {
        p0?.let {
            map = it
        }
        map.uiSettings.isTiltGesturesEnabled = false
        map.uiSettings.isScrollGesturesEnabled = false
        map.uiSettings.isScrollGesturesEnabledDuringRotateOrZoom = false
        map.uiSettings.isZoomGesturesEnabled = false

        map.addMarker(AppMapUtils.setMarkerOptions(this, "Blr", LatLng(53.893009, 27.567444)))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(53.893009, 27.567444), 3f))
    }

    private fun setAnimation() {
        ObjectAnimator.ofFloat(activityMainBinding.imageViewInfectedRound, "alpha", 0f, 1f).also {
            it.duration = 1200
            it.repeatMode = ValueAnimator.REVERSE
            it.repeatCount = Animation.INFINITE
            it.start()
        }
        ObjectAnimator.ofFloat(activityMainBinding.imageViewDeathsRound, "alpha", 0f, 1f).also {
            it.duration = 1200
            it.repeatMode = ValueAnimator.REVERSE
            it.repeatCount = Animation.INFINITE
            it.start()
        }
        ObjectAnimator.ofFloat(activityMainBinding.imageViewRecoveredRound, "alpha", 0f, 1f).also {
            it.duration = 1200
            it.repeatMode = ValueAnimator.REVERSE
            it.repeatCount = Animation.INFINITE
            it.start()
        }
    }
}