package com.mattg.smartcivics.models.committees


import com.google.gson.annotations.SerializedName

data class CommitteeHearingsResult(
    @SerializedName("copyright")
    var copyright: String?,
    @SerializedName("results")
    var results: List<ResultCommitteeHearing>?,
    @SerializedName("status")
    var status: String?
)