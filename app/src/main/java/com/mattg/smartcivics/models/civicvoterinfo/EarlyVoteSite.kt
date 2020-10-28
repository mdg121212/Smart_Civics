package com.mattg.smartcivics.models.civicvoterinfo


import com.google.gson.annotations.SerializedName

data class EarlyVoteSite(
    @SerializedName("address")
    var address: Address?,
    @SerializedName("endDate")
    var endDate: String?,
    @SerializedName("latitude")
    var latitude: Double?,
    @SerializedName("longitude")
    var longitude: Double?,
    @SerializedName("pollingHours")
    var pollingHours: String?,
    @SerializedName("sources")
    var sources: List<Source>?,
    @SerializedName("startDate")
    var startDate: String?
)