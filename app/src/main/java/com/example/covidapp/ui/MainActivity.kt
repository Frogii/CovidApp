package com.example.covidapp.ui

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        setAnimation()

        val mapBundle = savedInstanceState?.getBundle("MapBundle")
        activityMainBinding.mapView.onCreate(mapBundle)
        activityMainBinding.mapView.getMapAsync(this)
        val viewModelProviderFactory = MainViewModelProviderFactory(CovidRepository())
        mainViewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(MainViewModel::class.java)

        mainViewModel.getNameOfCountries().observe(this) { list ->
            val arrayAdapter = ArraySpinnerAdapter(this, list)
            activityMainBinding.mainSpinnerView.adapter = arrayAdapter
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
                        parent?.let {
                        val selectedItem = it.getItemAtPosition(position)
                        Toast.makeText(view.context, selectedItem.toString(), Toast.LENGTH_SHORT)
                            .show()
                        }
                    }
                }
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