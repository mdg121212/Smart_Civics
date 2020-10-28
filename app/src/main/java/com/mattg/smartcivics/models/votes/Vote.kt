package com.mattg.smartcivics.models.votes


import com.google.gson.annotations.SerializedName
import com.mattg.smartcivics.models.Bill
import com.mattg.smartcivics.models.Total

data class Vote(
    @SerializedName("bill")
    var bill: Bill?,
    @SerializedName("chamber")
    var chamber: String?,
    @SerializedName("congress")
    var congress: String?,
    @SerializedName("date")
    var date: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("member_id")
    var memberId: String?,
    @SerializedName("position")
    var position: String?,
    @SerializedName("question")
    var question: String?,
    @SerializedName("result")
    var result: String?,
    @SerializedName("roll_call")
    var rollCall: String?,
    @SerializedName("session")
    var session: String?,
    @SerializedName("time")
    var time: String?,
    @SerializedName("total")
    var total: Total?,
    @SerializedName("vote_uri")
    var voteUri: String?
)