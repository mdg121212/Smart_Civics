package com.mattg.smartcivics.models.civicapi


import com.google.gson.annotations.SerializedName

data class OcdDivisioncountryUs(
    @SerializedName("name")
    var name: String?,
    @SerializedName("officeIndices")
    var officeIndices: List<Int>?
)