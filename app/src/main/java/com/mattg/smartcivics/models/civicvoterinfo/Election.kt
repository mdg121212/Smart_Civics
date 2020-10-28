package com.mattg.smartcivics.models.civicvoterinfo


import com.google.gson.annotations.SerializedName

data class Election(
    @SerializedName("electionDay")
    var electionDay: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("ocdDivisionId")
    var ocdDivisionId: String?
)