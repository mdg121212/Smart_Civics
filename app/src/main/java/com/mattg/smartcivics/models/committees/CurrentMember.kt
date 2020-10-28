package com.mattg.smartcivics.models.committees


import com.google.gson.annotations.SerializedName

data class CurrentMember(
    @SerializedName("api_uri")
    var apiUri: String?,
    @SerializedName("begin_date")
    var beginDate: String?,
    @SerializedName("chamber")
    var chamber: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("note")
    var note: Any?,
    @SerializedName("party")
    var party: String?,
    @SerializedName("rank_in_party")
    var rankInParty: Any?,
    @SerializedName("side")
    var side: String?,
    @SerializedName("state")
    var state: String?
)