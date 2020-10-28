package com.mattg.smartcivics.models.bills


import com.google.gson.annotations.SerializedName

data class Action(
    @SerializedName("action_type")
    var actionType: String?,
    @SerializedName("chamber")
    var chamber: String?,
    @SerializedName("datetime")
    var datetime: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("id")
    var id: Int?
)