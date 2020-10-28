package com.mattg.smartcivics.models


import com.google.gson.annotations.SerializedName

data class MemberFundedTravelResult(
    @SerializedName("api_uri")
    var apiUri: String?,
    @SerializedName("copyright")
    var copyright: String?,
    @SerializedName("display_name")
    var displayName: String?,
    @SerializedName("member_id")
    var memberId: String?,
    @SerializedName("num_results")
    var numResults: Int?,
    @SerializedName("offset")
    var offset: Int?,
    @SerializedName("results")
    var results: List<ResultMemberFundedTravel>?,
    @SerializedName("status")
    var status: String?
)