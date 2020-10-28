package com.mattg.smartcivics.models.subcommittees


import com.google.gson.annotations.SerializedName

data class SubCommitteeResult(
    @SerializedName("copyright")
    var copyright: String?,
    @SerializedName("results")
    var results: List<ResultSubCommittee>?,
    @SerializedName("status")
    var status: String?
)