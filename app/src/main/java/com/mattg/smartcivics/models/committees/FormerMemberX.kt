package com.mattg.smartcivics.models.committees


import com.google.gson.annotations.SerializedName

data class FormerMemberX(
    @SerializedName("begin_date")
    var beginDate: String?,
    @SerializedName("chamber")
    var chamber: String?,
    @SerializedName("end_date")
    var endDate: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("note")
    var note: Any?,
    @SerializedName("party")
    var party: String?,
    @SerializedName("state")
    var state: String?,
    @SerializedName("url")
    var url: String?
)