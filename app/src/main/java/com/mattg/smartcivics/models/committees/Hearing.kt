package com.mattg.smartcivics.models.committees


import com.google.gson.annotations.SerializedName

data class Hearing(
    @SerializedName("api_uri")
    var apiUri: String?,
    @SerializedName("bill_ids")
    var billIds: List<String>?,
    @SerializedName("chamber")
    var chamber: String?,
    @SerializedName("committee")
    var committee: String?,
    @SerializedName("committee_code")
    var committeeCode: String?,
    @SerializedName("date")
    var date: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("location")
    var location: String?,
    @SerializedName("meeting_type")
    var meetingType: String?,
    @SerializedName("time")
    var time: String?,
    @SerializedName("url")
    var url: String?
)