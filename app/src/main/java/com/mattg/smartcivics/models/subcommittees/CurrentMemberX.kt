package com.mattg.smartcivics.models.subcommittees


import com.google.gson.annotations.SerializedName

data class CurrentMemberX(
    @SerializedName("api_uri")
    var apiUri: String?,
    @SerializedName("begin_date")
    var beginDate: Any?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("note")
    var note: Any?,
    @SerializedName("party")
    var party: String?,
    @SerializedName("rank_in_party")
    var rankInParty: Int?,
    @SerializedName("side")
    var side: String?,
    @SerializedName("state")
    var state: String?
)