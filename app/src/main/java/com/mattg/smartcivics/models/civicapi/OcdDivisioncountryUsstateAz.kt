package com.mattg.smartcivics.models.civicapi


import com.google.gson.annotations.SerializedName

data class OcdDivisioncountryUsstateAz(
    @SerializedName("name")
    var name: String?,
    @SerializedName("officeIndices")
    var officeIndices: List<Int>?
)