package com.mattg.smartcivics.models.civicapi


import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("city")
    var city: String?,
    @SerializedName("line1")
    var line1: String?,
    @SerializedName("state")
    var state: String?,
    @SerializedName("zip")
    var zip: String?
)