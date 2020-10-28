package com.mattg.smartcivics.models.civicvoterinfo.appvotermodel

import com.google.gson.annotations.SerializedName
import com.mattg.smartcivics.models.civicvoterinfo.Address
import com.mattg.smartcivics.models.civicvoterinfo.Source

data class GenericMapSite (
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