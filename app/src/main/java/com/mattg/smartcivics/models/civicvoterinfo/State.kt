package com.mattg.smartcivics.models.civicvoterinfo


import com.google.gson.annotations.SerializedName

data class State(
    @SerializedName("electionAdministrationBody")
    var electionAdministrationBody: ElectionAdministrationBody?,
    @SerializedName("local_jurisdiction")
    var localJurisdiction: LocalJurisdiction?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("sources")
    var sources: List<Source>?
)