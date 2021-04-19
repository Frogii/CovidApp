package com.example.covidapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.covidapp.databinding.ActivityMainBinding

abstract class MapActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

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