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
import com.example.covidapp.utils.Constants.NO_DATA
import com.example.covidapp.utils.Constants.mapBundle
import com.example.covidapp.utils.Resource
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.material.snackbar.Snackbar

class MainActivity : MapActivity(), OnMapReadyCallback {

    private var map: GoogleMap? = null
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        setAnimation()

        val mapBundle = savedInstanceState?.getBundle(mapBundle)
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
            it.uiSettings.isTiltGesturesEnabled = false
            it.uiSettings.isScrollGesturesEnabled = false
            it.uiSettings.isScrollGesturesEnabledDuringRotateOrZoom = false
        }

        mainViewModel.getCase().observe(this) { case ->
            activityMainBinding.apply {
                map?.clear()
                when (case) {
                    is Resource.Success -> {
                        hideProgress()
                        case.data?.let {
                            texViewInfectedCount.text = it.confirmed.toString()
                            texViewDeathsCount.text = it.deaths.toString()
                            texViewRecoveredCount.text = it.recovered.toString()
                            textViewNewestUpdate.text = resources.getString(
                                R.string.newest_update,
                                AppDateUtils.convertDateToNewest(it.date)
                            )
                            AppMapUtils.addMarker(
                                this@MainActivity,
                                map,
                                it.lat,
                                it.lon,
                                it.country
                            )
                        }
                    }
                    is Resource.Error -> {
                        hideProgress()
                        texViewInfectedCount.text = getString(R.string.no_cases)
                        texViewDeathsCount.text = getString(R.string.no_cases)
                        texViewRecoveredCount.text = getString(R.string.no_cases)
                        textViewNewestUpdate.text = getString(R.string.no_info)
                        if (case.message.equals(NO_DATA)) {
                            Snackbar.make(
                                activityMainBinding.root, getString(R.string.snackbar_no_info),
                                Snackbar.LENGTH_LONG
                            ).show()
                        } else {
                            Snackbar.make(
                                activityMainBinding.root, getString(R.string.network_error),
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                    }
                    is Resource.Loading -> {
                        showProgress()
                    }
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

    private fun showProgress() {
        activityMainBinding.texViewInfectedCount.visibility = View.INVISIBLE
        activityMainBinding.texViewDeathsCount.visibility = View.INVISIBLE
        activityMainBinding.texViewRecoveredCount.visibility = View.INVISIBLE
        activityMainBinding.progressBarMain.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        activityMainBinding.progressBarMain.visibility = View.INVISIBLE
        activityMainBinding.texViewInfectedCount.visibility = View.VISIBLE
        activityMainBinding.texViewDeathsCount.visibility = View.VISIBLE
        activityMainBinding.texViewRecoveredCount.visibility = View.VISIBLE
    }
}
