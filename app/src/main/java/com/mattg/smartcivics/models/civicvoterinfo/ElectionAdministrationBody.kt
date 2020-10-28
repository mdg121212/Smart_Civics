package com.mattg.smartcivics.models.civicvoterinfo


import com.google.gson.annotations.SerializedName

data class ElectionAdministrationBody(
    @SerializedName("absenteeVotingInfoUrl")
    var absenteeVotingInfoUrl: String?,
    @SerializedName("electionInfoUrl")
    var electionInfoUrl: String?,
    @SerializedName("electionRegistrationConfirmationUrl")
    var electionRegistrationConfirmationUrl: String?,
    @SerializedName("electionRegistrationUrl")
    var electionRegistrationUrl: String?,
    @SerializedName("votingLocationFinderUrl")
    var votingLocationFinderUrl: String?
)