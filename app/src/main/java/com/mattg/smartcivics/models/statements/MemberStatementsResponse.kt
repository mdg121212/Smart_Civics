package com.mattg.smartcivics.models.statements


import com.google.gson.annotations.SerializedName

data class MemberStatementsResponse(
    @SerializedName("congress")
    var congress: Int?,
    @SerializedName("copyright")
    var copyright: String?,
    @SerializedName("member_id")
    var memberId: String?,
    @SerializedName("member_uri")
    var memberUri: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("num_results")
    var numResults: Int?,
    @SerializedName("offset")
    var offset: Int?,
    @SerializedName("results")
    var results: List<ResultMemberStatement>?,
    @SerializedName("status")
    var status: String?
)