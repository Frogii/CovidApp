package com.example.covidapp.utils

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import com.example.covidapp.R
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class AppMapUtils {

    companion object {
        fun setMarkerOptions(context: Context, countryName: String, latLng: LatLng): MarkerOptions {
            val markerResourcePath = R.drawable.ic_map_pin
            val bitmapVector = AppCompatResources.getDrawable(context, markerResourcePath)?.toBitmap()
            return MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.fromBitmap(bitmapVector))
                .title(countryName)
        }
    }
}