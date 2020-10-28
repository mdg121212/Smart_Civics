package com.mattg.smartcivics.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Looper
import android.view.View
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.android.material.snackbar.Snackbar

class LocationFinder(private val mActivity: Activity, private val view: View) {

    lateinit var mFusedLocationClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    var searchStringFormatted: String? = null

    fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            mActivity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER)
    }


    fun getNewLocation() : String? {
        if(isLocationEnabled()){
            mFusedLocationClient = mActivity.let { LocationServices.getFusedLocationProviderClient(it)}!!
            locationRequest = LocationRequest().apply {
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                interval = 0
                fastestInterval = 0
                numUpdates = 1
            }
            if (ActivityCompat.checkSelfPermission(mActivity,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    mActivity,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.checkSelfPermission(mActivity, Manifest.permission_group.LOCATION)
            }
            mFusedLocationClient.requestLocationUpdates(
                locationRequest, locationCallback, Looper.getMainLooper()
            )
            return searchStringFormatted
        } else {
            Snackbar.make(view, "Need to enable location services", Snackbar.LENGTH_SHORT).show()
            return ""
        }


    }

    private val locationCallback = object: LocationCallback(){
        override fun onLocationResult(location: LocationResult?) {
            val lastLocation = location?.lastLocation
            //set new location
            if(lastLocation != null){
                val string = getLocationReturnAddress(lastLocation.latitude, lastLocation.longitude, mActivity)
                searchStringFormatted = string
            } else {
                Snackbar.make(view, "Couldn't find location", Snackbar.LENGTH_SHORT).show()
            }

        }
    }
}