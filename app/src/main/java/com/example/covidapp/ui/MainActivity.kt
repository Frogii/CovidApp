package com.example.covidapp.ui

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.covidapp.R
import com.example.covidapp.adapter.ArraySpinnerAdapter
import com.example.covidapp.databinding.ActivityMainBinding
import com.example.covidapp.model.CountryItem
import com.example.covidapp.repository.CovidRepository
import com.example.covidapp.utils.AppDateUtils
import com.example.covidapp.utils.AppMapUtils
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.material.snackbar.Snackbar

class MainActivity : MapActivity(), OnMapReadyCallback {

    lateinit var map: GoogleMap
    lateinit var mainViewModel: MainViewModel
    private var marker: Marker? = null

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

        mainViewModel.getCountries().observe(this) { list ->
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
                            val selectedItem = it.getItemAtPosition(position) as CountryItem
                            mainViewModel.getCases(selectedItem.lowerCaseName)
                        }
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

        mainViewModel.getCase().observe(this) { case ->
            activityMainBinding.apply {
                marker?.remove()
                texViewInfectedCount.text = case.confirmed.toString()
                texViewDeathsCount.text = case.deaths.toString()
                texViewRecoveredCount.text = case.recovered.toString()
                if (case.date != "") {
                    textViewNewestUpdate.text = resources.getString(
                        R.string.newest_update,
                        AppDateUtils.convertDateToNewest(case.date)
                    )
                } else {
                    textViewNewestUpdate.text = getString(R.string.no_info)
                    Snackbar.make(
                        activityMainBinding.root, getString(R.string.snackbar_no_info),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
                val latLng = LatLng(case.lat.toDouble(), case.lon.toDouble())
                if (case.lat != "0" && case.lon != "0") {
                    marker = map.addMarker(
                        AppMapUtils.setMarkerOptions(
                            this@MainActivity,
                            case.country,
                            latLng
                        )
                    )
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 3f))
                }
            }
        }
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