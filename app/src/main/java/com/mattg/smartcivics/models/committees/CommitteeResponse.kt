package com.mattg.smartcivics.models.committees


import com.google.gson.annotations.SerializedName

data class CommitteeResponse(
    @SerializedName("copyright")
    var copyright: String?,
    @SerializedName("results")
    var results: List<ResultSingleCommittee>?,
    @SerializedName("status")
    var status: String?
)