package com.mattg.smartcivics.models.opensecrets


import com.google.gson.annotations.SerializedName

data class Attributes(
    @SerializedName("cand_name")
    var candName: String?,
    @SerializedName("cid")
    var cid: String?,
    @SerializedName("cycle")
    var cycle: String?,
    @SerializedName("notice")
    var notice: String?,
    @SerializedName("origin")
    var origin: String?,
    @SerializedName("source")
    var source: String?
)