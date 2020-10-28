package com.mattg.smartcivics.models.votes


import com.google.gson.annotations.SerializedName

data class MemberVoteResult(
    @SerializedName("copyright")
    var copyright: String?,
    @SerializedName("results")
    var results: List<ResultVoteDetail>?,
    @SerializedName("status")
    var status: String?
)