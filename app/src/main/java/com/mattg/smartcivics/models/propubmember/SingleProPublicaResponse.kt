package com.mattg.smartcivics.models.propubmember


import com.google.gson.annotations.SerializedName

data class SingleProPublicaResponse(
    @SerializedName("copyright")
    var copyright: String?,
    @SerializedName("results")
    var results: List<ResultX>?,
    @SerializedName("status")
    var status: String?
)