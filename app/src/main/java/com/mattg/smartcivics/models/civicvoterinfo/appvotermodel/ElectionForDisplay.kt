package com.mattg.smartcivics.models.civicvoterinfo.appvotermodel

import com.google.gson.annotations.SerializedName
import com.mattg.smartcivics.models.civicvoterinfo.DropOffLocation
import com.mattg.smartcivics.models.civicvoterinfo.EarlyVoteSite
import com.mattg.smartcivics.models.civicvoterinfo.PollingLocation

data class ElectionForDisplay (
    @SerializedName("title")
    val title: String?,
    @SerializedName("dropoffLocations")
    val dropoffLocations: List<DropOffLocation>?,
    @SerializedName("earlyVoteSites")
    val earlyVoteSites: List<EarlyVoteSite>?,
    @SerializedName("pollingLocations")
    val pollingLocations: List<PollingLocation>?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("absenteeVotingInfoUrl")
    var absenteeVotingInfoUrl: String?,
    @SerializedName("electionInfoUrl")
    var electionInfoUrl: String?,
    @SerializedName("electionRegistrationConfirmationUrl")
    var electionRegistrationConfirmationUrl: String?,
    @SerializedName("electionRegistrationUrl")
    var electionRegistrationUrl: String?,
    @SerializedName("votingLocationFinderUrl")
    var votingLocationFinderUrl: String?,

)