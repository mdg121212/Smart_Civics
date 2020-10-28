package com.mattg.smartcivics.models.finance


import com.google.gson.annotations.SerializedName

data class FinanceResponse(
    @SerializedName("base_uri")
    var baseUri: String?,
    @SerializedName("copyright")
    var copyright: String?,
    @SerializedName("cycle")
    var cycle: Int?,
    @SerializedName("results")
    var results: List<ResultFinance>?,
    @SerializedName("status")
    var status: String?
)