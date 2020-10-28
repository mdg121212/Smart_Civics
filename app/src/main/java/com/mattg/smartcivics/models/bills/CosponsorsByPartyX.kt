package com.mattg.smartcivics.models.bills


import com.google.gson.annotations.SerializedName

data class CosponsorsByPartyX(
    @SerializedName("D")
    var d: Int?,
    @SerializedName("R")
    var r: Int?
)