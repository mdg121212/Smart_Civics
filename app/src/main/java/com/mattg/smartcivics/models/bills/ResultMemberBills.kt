package com.mattg.smartcivics.models.bills


import com.google.gson.annotations.SerializedName

data class ResultMemberBills(
    @SerializedName("bills")
    var bills: List<BillX>?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("member_uri")
    var memberUri: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("num_results")
    var numResults: Int?,
    @SerializedName("offset")
    var offset: Int?
)