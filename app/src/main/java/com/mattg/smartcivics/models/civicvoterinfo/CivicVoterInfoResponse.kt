package com.mattg.smartcivics.models.civicvoterinfo


import com.google.gson.annotations.SerializedName

data class CivicVoterInfoResponse(
    @SerializedName("dropOffLocations")
    var dropOffLocations: List<DropOffLocation>?,
    @SerializedName("earlyVoteSites")
    var earlyVoteSites: List<EarlyVoteSite>?,
    @SerializedName("election")
    var election: Election?,
    @SerializedName("kind")
    var kind: String?,
    @SerializedName("normalizedInput")
    var normalizedInput: NormalizedInput?,
    @SerializedName("pollingLocations")
    var pollingLocations: List<PollingLocation>?,
    @SerializedName("state")
    var state: List<State>?
)