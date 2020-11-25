package com.mattg.smartcivics.ui.elections.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.material.snackbar.Snackbar
import com.mattg.smartcivics.MainActivity
import com.mattg.smartcivics.R
import com.mattg.smartcivics.models.civicvoterinfo.appvotermodel.GenericMapSite
import com.mattg.smartcivics.ui.elections.ElectionsViewModel
import com.mattg.smartcivics.utils.TypeForMap
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.map_custom_info_window.view.*

class ElectionMapsFragment : Fragment(), OnMapReadyCallback,
    GoogleMap.OnMarkerClickListener,
    GoogleMap.InfoWindowAdapter
{

    lateinit var list: List<GenericMapSite>
    private lateinit var viewModel : ElectionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_election_maps, container, false)
    }



    override fun onMarkerClick(marker: Marker?): Boolean {
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        viewModel = ViewModelProvider(requireActivity()).get(ElectionsViewModel::class.java)
        observeViewModel()
        Snackbar.make(requireView(),
            "Click a marker to see the location name, then click the name for more detail",
            Snackbar.LENGTH_LONG).show()
        mapFragment.getMapAsync(this)
    }

    private fun observeViewModel(){
        viewModel.apply {
            text.observe(viewLifecycleOwner){
                Log.i("WHICHLIST", it)
                when (it){
                    TypeForMap.EARLYVOTE.toString() -> {
                        (requireActivity() as MainActivity).setActionBarTitle("Early voting locations")
                        viewModel.genericListMapSiteEarly.observe(viewLifecycleOwner){ earlyList ->
                            list = earlyList
                        }
                    }
                    TypeForMap.DROPOFF.toString() -> {
                        (requireActivity() as MainActivity).setActionBarTitle("Early drop off locations")
                        viewModel.genericListMapSiteDropOff.observe(viewLifecycleOwner){ dropOffList ->
                            list = dropOffList
                        }
                    }
                    TypeForMap.POLLING.toString() -> {
                        (requireActivity() as MainActivity).setActionBarTitle("Polling locations")
                        viewModel.genericListMapSitePolling.observe(viewLifecycleOwner){ pollingList ->
                            list = pollingList
                        }
                    }
                }
            }

            }


    }

    override fun getInfoWindow(p0: Marker?): View? {
        return null
    }

    override fun getInfoContents(p0: Marker?): View {
        return layoutInflater.inflate(R.layout.map_custom_info_window, container)
    }

    @SuppressLint("SetTextI18n", "InflateParams")
    override fun onMapReady(map: GoogleMap?) {
        // populate map with list points
        if (ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        map?.setMapStyle(
            MapStyleOptions.loadRawResourceStyle(
                requireContext(),
                R.raw.map_style
            )
        )
        map?.isMyLocationEnabled = true
        map?.isTrafficEnabled = true
        map?.isBuildingsEnabled = true

        for (item in list) {

            populateMap(item, map)

            map?.setOnInfoWindowClickListener {marker ->
                    val info: GenericMapSite = marker.tag as GenericMapSite
                    val inflater = LayoutInflater.from(context)
                    val view = inflater.inflate(R.layout.map_custom_info_window, null)
                    view.apply {
                        tv_info_title.text = info.address?.locationName
                        val split = info.pollingHours?.split(";")
                        var pollString = ""
                        if (split != null) {
                            for (string in split){
                            pollString += "$string\n"
                            }
                        }
                        tv_polling_hours.text = pollString
                        tv_info_address.text = info.address?.line1 + " " + info.address?.city + ", " + info.address?.state + " " +info.address?.zip
                        if(info.startDate != null && info.endDate != null){
                            tv_info_dates.text = ("${info.startDate} - ${info.endDate}")
                        } else {
                            tv_info_dates.text = "Election Day"
                        }

                    }
                showMarkerDialog(view, marker)
            }

        }
        val zoom = 12.7f

        val markerRef = LatLng(list[0].latitude!!, list[0].longitude!!)
            //centering camera on first location from list
            map?.moveCamera(CameraUpdateFactory.newLatLngZoom(markerRef, zoom))
    }

    private fun showMarkerDialog(
        view: View?,
        marker: Marker,
    ) {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setView(view)
        dialog.setPositiveButton("Get Directions") { _, _ ->
            //open google maps with directions avoiding tolls
            getDirectionsFromMarker(marker)
        }
            .setNegativeButton("Close") { di, _ ->
                di.cancel()
            }.show()
    }

    private fun getDirectionsFromMarker(marker: Marker) {
        val gmmIntentUri =
            Uri.parse("google.navigation:q=${marker.position?.latitude},${marker.position?.longitude}&avoid=tf")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

    private fun populateMap(
        it: GenericMapSite,
        map: GoogleMap?,
    ) {
        if (it.latitude != null && it.longitude != null) {
            val latLong = LatLng(it.latitude!!, it.longitude!!)

            val marker = map?.addMarker(MarkerOptions().apply {
                position(latLong)
                title("${it.address?.locationName}")
                snippet("${it.address?.line1}")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
            })
            marker?.tag = it
            marker?.showInfoWindow()
        }
    }

}

