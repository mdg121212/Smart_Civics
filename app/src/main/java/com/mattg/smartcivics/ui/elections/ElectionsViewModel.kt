package com.mattg.smartcivics.ui.elections

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mattg.smartcivics.models.civicvoterinfo.CivicVoterInfoResponse
import com.mattg.smartcivics.models.civicvoterinfo.DropOffLocation
import com.mattg.smartcivics.models.civicvoterinfo.EarlyVoteSite
import com.mattg.smartcivics.models.civicvoterinfo.PollingLocation
import com.mattg.smartcivics.models.civicvoterinfo.appvotermodel.ElectionForDisplay
import com.mattg.smartcivics.models.civicvoterinfo.appvotermodel.GenericMapSite
import com.mattg.smartcivics.network.ApiCallService
import com.mattg.smartcivics.utils.TypeForMap
import com.mattg.smartcivics.utils.createDisplayElection
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class ElectionsViewModel : ViewModel() {

    private val _textType = MutableLiveData<String>()
    val text: LiveData<String> = _textType

    private val _areElections = MutableLiveData<Boolean>()
    val areElections: LiveData<Boolean> = _areElections

    private val _genericListMapLocationsPolling = MutableLiveData<List<GenericMapSite>>()
    val genericListMapSitePolling: LiveData<List<GenericMapSite>> = _genericListMapLocationsPolling
    private val _genericListMapLocationsDropOff = MutableLiveData<List<GenericMapSite>>()
    val genericListMapSiteDropOff: LiveData<List<GenericMapSite>> = _genericListMapLocationsDropOff
    private val _genericListMapLocationsEarly = MutableLiveData<List<GenericMapSite>>()
    val genericListMapSiteEarly: LiveData<List<GenericMapSite>> = _genericListMapLocationsEarly

    private val _pollingLocations = MutableLiveData<List<PollingLocation>>()
    val pollingLocation: LiveData<List<PollingLocation>> = _pollingLocations

    private val _dropOffLocations = MutableLiveData<List<DropOffLocation>>()
    val dropOffLocations: LiveData<List<DropOffLocation>> = _dropOffLocations

    private val _earlyVoteSites = MutableLiveData<List<EarlyVoteSite>>()
    val earlyVoteSites: LiveData<List<EarlyVoteSite>> = _earlyVoteSites


    private val _voteInfoResponse = MutableLiveData<CivicVoterInfoResponse>()
    val voterInfoResponse: LiveData<CivicVoterInfoResponse> = _voteInfoResponse

    private val _electionFormatted = MutableLiveData<List<ElectionForDisplay>>()
    val electionFormatted: LiveData<List<ElectionForDisplay>> = _electionFormatted

    private val _electionId = MutableLiveData<String>()
    val electionId: LiveData<String> = _electionId

    fun getVoterInfo(address: String, allInfo: Boolean, key: String, type: String?) {
        val callInfoApi = ApiCallService.get()
        callInfoApi.getVoterInformationBase(address, allInfo, key).enqueue(object :
            Callback<CivicVoterInfoResponse> {
            override fun onResponse(
                call: Call<CivicVoterInfoResponse>,
                response: Response<CivicVoterInfoResponse>,
            ) {
                val result = response.body()
                _voteInfoResponse.value = result
                Log.i("TEST", "Voter info response: ${response.body()}")
                val formatted = response.body()?.let { createDisplayElection(it) }
                if (formatted != null) {
                    val electionList = listOf(formatted)
                    Log.i("TESTING", "${ electionList.size }, ${electionList.get(0).date}")
                    _electionFormatted.value = electionList
                    //launch on background thread
                    viewModelScope.launch {
                        getEarlyList()?.let{ electionList[0].earlyVoteSites?.let { it1 ->
                            convertEarlyList(it1)}}
                        getDropOffList()?.let{ electionList[0].dropoffLocations?.let { it2 ->
                            convertDropOffList(it2)}}
                        getPollingList()?.let { electionList[0].pollingLocations?.let { it3 ->
                            convertPollingList(it3)}}
                    }

                    if(type != null){
                       when(type){
                           TypeForMap.POLLING.toString() -> {
                               setType(TypeForMap.POLLING.toString())
                           }
                           TypeForMap.DROPOFF.toString() -> {
                               setType(TypeForMap.DROPOFF.toString())
                           }
                           TypeForMap.EARLYVOTE.toString() -> {
                               setType(TypeForMap.EARLYVOTE.toString())
                           }
                       }
                    }
                    _areElections.value = true

                } else {

                    _areElections.value = false
                }

            }

            override fun onFailure(call: Call<CivicVoterInfoResponse>, t: Throwable) {

            }
        })
    }

    private fun convertPollingList(list: List<PollingLocation>) {
        val newList = ArrayList<GenericMapSite>()
        for(item in list){
            val newItem = GenericMapSite(
                item.address,
                item.endDate,
                item.latitude,
                item.longitude,
                item.pollingHours,
                item.sources,
                item.startDate
            )
            newList.add(newItem)
        }
        _genericListMapLocationsPolling.value = newList.toList()

    }

    private fun convertDropOffList(list: List<DropOffLocation>) {
        val newList = ArrayList<GenericMapSite>()
        for(item in list){
            val newItem = GenericMapSite(
                item.address,
                item.endDate,
                item.latitude,
                item.longitude,
                item.pollingHours,
                item.sources,
                item.startDate
            )
            newList.add(newItem)
        }
        _genericListMapLocationsDropOff.value = newList.toList()

    }
    private fun convertEarlyList(list: List<EarlyVoteSite>) {
        val newList = ArrayList<GenericMapSite>()
        for(item in list){
            val newItem = GenericMapSite(
                item.address,
                item.endDate,
                item.latitude,
                item.longitude,
                item.pollingHours,
                item.sources,
                item.startDate
            )
            newList.add(newItem)
        }
        _genericListMapLocationsEarly.value = newList.toList()

    }

    fun setType(type: String){
        _textType.value = type
    }

    private fun getDropOffList(): List<DropOffLocation>? {
        if (!_electionFormatted.value.isNullOrEmpty()) {
            val listLocation = _electionFormatted.value
            val dropOffList = listLocation?.get(0)?.dropoffLocations
            _dropOffLocations.postValue(dropOffList)
            return dropOffList
        }
        return null
    }
    private fun getPollingList(): List<PollingLocation>? {
        if(!_electionFormatted.value.isNullOrEmpty()) {
            val listLocation = _electionFormatted.value
            val pollingList = listLocation?.get(0)?.pollingLocations
            _pollingLocations.postValue(pollingList)
            return pollingList
        }
        return null
    }
    private fun getEarlyList() : List<EarlyVoteSite>? {
        if (!_electionFormatted.value.isNullOrEmpty()) {
            val listLocation = _electionFormatted.value
            val earlyList = listLocation?.get(0)?.earlyVoteSites
            _earlyVoteSites.postValue(earlyList)
            return earlyList
        }
        return null
    }
}