package com.mattg.smartcivics.models.bills


import com.google.gson.annotations.SerializedName

data class CosponsorsByParty(
    @SerializedName("D")
    var d: Int?
)