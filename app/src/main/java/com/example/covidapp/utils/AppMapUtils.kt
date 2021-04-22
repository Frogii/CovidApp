package com.example.covidapp.utils

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import com.example.covidapp.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class AppMapUtils {

    companion object {

        private const val zoomLvl = 3f

        private fun setMarkerOptions(
            context: Context,
            countryName: String,
            latLng: LatLng
        ): MarkerOptions {
            val markerResourcePath = R.drawable.ic_map_pin
            val bitmapVector =
                AppCompatResources.getDrawable(context, markerResourcePath)?.toBitmap()
            return MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.fromBitmap(bitmapVector))
                .title(countryName)
        }

        fun addMarker(
            context: Context,
            map: GoogleMap?,
            lat: String,
            lon: String,
            countryName: String
        ) {
            val latLng = LatLng(lat.toDouble(), lon.toDouble())
            val marker = setMarkerOptions(context, countryName, latLng)
            map?.addMarker(marker)
            map?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLvl))
        }
    }
}