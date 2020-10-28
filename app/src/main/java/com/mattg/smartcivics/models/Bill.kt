package com.mattg.smartcivics.models


import com.google.gson.annotations.SerializedName

data class Bill(
    @SerializedName("bill_id")
    var billId: String?,
    @SerializedName("bill_uri")
    var billUri: String?,
    @SerializedName("latest_action")
    var latestAction: String?,
    @SerializedName("number")
    var number: String?,
    @SerializedName("title")
    var title: String?
)