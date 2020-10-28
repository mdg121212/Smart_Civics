package com.mattg.smartcivics.models.committees


import com.google.gson.annotations.SerializedName

data class ResultCommitteeHearing(
    @SerializedName("congress")
    var congress: String?,
    @SerializedName("hearings")
    var hearings: List<Hearing>?,
    @SerializedName("num_results")
    var numResults: Int?,
    @SerializedName("offset")
    var offset: Int?
)