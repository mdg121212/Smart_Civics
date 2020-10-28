package com.mattg.smartcivics.models.propubmember


import com.google.gson.annotations.SerializedName

data class Committee(
    @SerializedName("api_uri")
    var apiUri: String?,
    @SerializedName("begin_date")
    var beginDate: String?,
    @SerializedName("code")
    var code: String?,
    @SerializedName("end_date")
    var endDate: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("rank_in_party")
    var rankInParty: Int?,
    @SerializedName("side")
    var side: String?,
    @SerializedName("title")
    var title: String?
)