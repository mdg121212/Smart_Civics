package com.mattg.smartcivics.models.civicvoterinfo


import com.google.gson.annotations.SerializedName

data class LocalJurisdiction(
    @SerializedName("electionAdministrationBody")
    var electionAdministrationBody: ElectionAdministrationBody?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("sources")
    var sources: List<Source>?
)