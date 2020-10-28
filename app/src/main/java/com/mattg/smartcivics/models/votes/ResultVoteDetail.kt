package com.mattg.smartcivics.models.votes


import com.google.gson.annotations.SerializedName

data class ResultVoteDetail(
    @SerializedName("member_id")
    var memberId: String?,
    @SerializedName("offset")
    var offset: String?,
    @SerializedName("total_votes")
    var totalVotes: String?,
    @SerializedName("votes")
    var votes: List<Vote>?
)