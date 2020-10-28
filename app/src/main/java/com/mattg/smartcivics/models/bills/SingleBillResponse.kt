package com.mattg.smartcivics.models.bills


import com.google.gson.annotations.SerializedName

data class SingleBillResponse(
    @SerializedName("copyright")
    var copyright: String?,
    @SerializedName("results")
    var results: List<SingleBillResult>?,
    @SerializedName("status")
    var status: String?
)