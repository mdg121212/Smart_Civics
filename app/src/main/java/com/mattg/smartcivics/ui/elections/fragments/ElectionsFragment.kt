package com.mattg.smartcivics.ui.elections.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.*
import com.google.android.material.snackbar.Snackbar
import com.mattg.smartcivics.R
import com.mattg.smartcivics.models.civicvoterinfo.appvotermodel.ElectionForDisplay
import com.mattg.smartcivics.ui.elections.ElectionsViewModel
import com.mattg.smartcivics.ui.elections.adapters.ElectionAdapter
import com.mattg.smartcivics.ui.home.ElectionClickListener
import com.mattg.smartcivics.utils.Constants
import com.mattg.smartcivics.utils.TypeForMap
import com.mattg.smartcivics.utils.getLocationReturnAddress
import kotlinx.android.synthetic.main.election_admin_info_dialog.view.*
import kotlinx.android.synthetic.main.fragment_elections.*

class ElectionsFragment : Fragment() {

    private lateinit var viewModel: ElectionsViewModel
    private lateinit var clickListener: ElectionClickListener
    private var searchStringFormatted: String? = null
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(requireActivity()).get(ElectionsViewModel::class.java)
        observeViewModel()
        return inflater.inflate(R.layout.fragment_elections, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()

        btn_election_location.setOnClickListener {

            getNewLocation()

        }


        btn_address.setOnClickListener {
            if (et_election_address.text != null) {
                searchStringFormatted = et_election_address.text.toString()
                tv_address_display.text = searchStringFormatted
                viewModel.getVoterInfo(searchStringFormatted!!, true, Constants.key, null)
            } else {
                Toast.makeText(requireContext(), "Please enter address", Toast.LENGTH_SHORT).show()
            }

        }

    }


    private fun initRecycler(list: List<ElectionForDisplay>, clickListener: ElectionClickListener) {
        val electionAdapter = ElectionAdapter(list, clickListener)
        val electionLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rv_viewscreen.apply {
            adapter = electionAdapter
            layoutManager = electionLayoutManager
        }
    }

    @SuppressLint("InflateParams")
    private fun observeViewModel() {
        viewModel.apply {
            areElections.observe(viewLifecycleOwner) {
                when (it) {
                    false -> showNoElections()
                    true -> {
                        electionFormatted.observe(viewLifecycleOwner) { list ->
                            if (list != null) {

                                clickListener = ElectionClickListener { election, _, type ->
                                    searchStringFormatted?.let { it1 ->
                                        viewModel.getVoterInfo(
                                            it1,
                                            true,
                                            Constants.key,
                                            type
                                        )
                                    }
                                    viewModel.setType(type)
                                    if (type == TypeForMap.ADMININFO.toString()) {
                                        showAdminDialog(election)
                                    } else {
                                        findNavController().navigate(R.id.action_navigation_dashboard_to_electionMapsFragment)
                                    }
                                }

                                initRecycler(list, clickListener)
                            }

                        }
                    }
                }
            }
        }
    }

    private fun showNoElections() {
        rv_viewscreen.visibility = View.INVISIBLE
        cv_no_elections.visibility = View.VISIBLE
    }

    @SuppressLint("InflateParams")
    private fun showAdminDialog(election: ElectionForDisplay) {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.election_admin_info_dialog, null)
        view.apply {
            tv_main_link.text = election.electionInfoUrl ?: "Not Available"
            tv_voting_location_link.text = election.votingLocationFinderUrl ?: "Not Available"
            tv_registration_link.text = election.electionRegistrationUrl ?: "Not Available"
            tv_registration_conf_link.text =
                election.electionRegistrationConfirmationUrl ?: "Not Available"
            tv_absentee_voting_link.text = election.absenteeVotingInfoUrl ?: "Not Available"
        }
        showElectionAdminDetails(view)
    }

    private fun showElectionAdminDetails(view: View) {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setView(view)
        dialog.setTitle("Election Administration Information")
        dialog.show()

    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
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
                viewModel.getVoterInfo(string, true, Constants.key, null)
                tv_address_display.text = string
            } else {
                Snackbar.make(requireView(), "Couldn't find location", Snackbar.LENGTH_SHORT).show()
            }

        }
    }


}
