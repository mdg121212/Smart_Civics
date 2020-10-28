package com.mattg.smartcivics.models.expenses


import com.google.gson.annotations.SerializedName

data class MemberQuarterlyExpensesResponse(
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
    @SerializedName("quarter")
    var quarter: Int?,
    @SerializedName("results")
    var results: List<ResultMemberQuarterlyExpense>?,
    @SerializedName("status")
    var status: String?,
    @SerializedName("year")
    var year: Int?
)