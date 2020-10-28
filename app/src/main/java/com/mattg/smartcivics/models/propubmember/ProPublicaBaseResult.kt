package com.mattg.smartcivics.models.propubmember


import com.google.gson.annotations.SerializedName

data class ProPublicaBaseResult(
    @SerializedName("copyright")
    var copyright: String?,
    @SerializedName("results")
    var results: List<Result>?,
    @SerializedName("status")
    var status: String?
)