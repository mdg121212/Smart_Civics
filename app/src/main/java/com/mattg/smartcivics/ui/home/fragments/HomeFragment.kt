package com.mattg.smartcivics.ui.home.fragments

import android.annotation.SuppressLint
import android.content.Context.LOCATION_SERVICE
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.*
import com.google.android.material.snackbar.Snackbar
import com.mattg.smartcivics.R
import com.mattg.smartcivics.models.Representative
import com.mattg.smartcivics.ui.home.HomeViewModel
import com.mattg.smartcivics.ui.home.RecyclerClickListener
import com.mattg.smartcivics.ui.home.adapters.RepAdapter
import com.mattg.smartcivics.utils.Constants
import com.mattg.smartcivics.utils.getLocationReturnAddress
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment() {

    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var viewModel: HomeViewModel
    private lateinit var clickListener: RecyclerClickListener
    private var homeRepList = ArrayList<Representative>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()

        btn_home_search.setOnClickListener {
            viewModel.clearList()
            viewModel.clearAddress()
            //creating address search string
            if (et_home_address.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), "Please fill out address", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val searchString =
                    "${et_home_address.text}"
                callApis(searchString)
                et_home_address.text.clear()

                viewModel.setAddress(searchString.format(Locale.getDefault()))

            }
        }
        btn_use_location.setOnClickListener {
            et_home_address.text.clear()
            viewModel.clearList()
            viewModel.clearAddress()
            getNewLocation()

        }

    }

    private fun callApis(searchString: String) {
        viewModel.viewModelScope.launch {
            viewModel.callApi(searchString, Constants.key)
            viewModel.callProPublicaAllMembers(116, "senate")
            viewModel.callProPublicaAllMembers(116, "house")
        }
    }

    private fun observeViewModel() {
        viewModel.officials.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                setupRecycler(it)
                homeRepList = it

                viewModel.callProPublicaAllMembers(116, "house")
                viewModel.callProPublicaAllMembers(116, "senate")
            }
        }

        viewModel.apply {
            addressString.observe(viewLifecycleOwner) {
                if (!it.isNullOrEmpty()) {
                    tv_home_address_display.text = it
                }
            }
            progressShowing.observe(viewLifecycleOwner) {
                if (it) {
                    progressBar_mainRecycler.visibility = View.VISIBLE
                } else {
                    progressBar_mainRecycler.visibility = View.INVISIBLE
                }
            }
            recyclerMainShowing.observe(viewLifecycleOwner) {
                if (it) {
                    rv_home.visibility = View.VISIBLE
                } else {
                    rv_home.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun setupRecycler(list: ArrayList<Representative>) {
        clickListener = RecyclerClickListener { representative, _ ->
            //setting the rating to view in more detail clicked fragment
            representative.againstPartyRating?.let { viewModel.setRepVoteAgainstRating(it) }
            viewModel.setRepToView(representative)
            findNavController().navigate(
                HomeFragmentDirections.actionNavigationHomeToRepDetailFragment(
                    representative.name
                )
            )
        }
        val recycler = rv_home
        val adapterReps = RepAdapter(requireActivity(), list, clickListener)
        val manager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recycler.apply {
            adapter = adapterReps
            layoutManager = manager
        }

    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            requireActivity().getSystemService(LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    @SuppressLint("MissingPermission")
    fun getNewLocation() {
        if (isLocationEnabled()) {
            mFusedLocationClient =
                activity?.let { LocationServices.getFusedLocationProviderClient(it) }!!
            locationRequest = LocationRequest().apply {
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                interval = 0
                fastestInterval = 0
                numUpdates = 1
            }
            mFusedLocationClient.requestLocationUpdates(
                locationRequest, locationCallback, Looper.getMainLooper()
            )
        } else {
            Snackbar.make(requireView(), "Need to enable location services", Snackbar.LENGTH_SHORT)
                .show()
        }


    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(location: LocationResult?) {
            val lastLocation = location?.lastLocation
            //set new location
            if (lastLocation != null) {
                val string = getLocationReturnAddress(
                    lastLocation.latitude,
                    lastLocation.longitude,
                    requireContext()
                )
                callApis(string)
                tv_home_address_display.text = string
            } else {
                Snackbar.make(requireView(), "Couldn't find location", Snackbar.LENGTH_SHORT).show()
            }

        }
    }

}



