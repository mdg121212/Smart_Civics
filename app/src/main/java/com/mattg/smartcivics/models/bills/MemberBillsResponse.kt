package com.mattg.smartcivics.models.bills


import com.google.gson.annotations.SerializedName

data class MemberBillsResponse(
    @SerializedName("copyright")
    var copyright: String?,
    @SerializedName("results")
    var results: List<ResultMemberBills>?,
    @SerializedName("status")
    var status: String?
)