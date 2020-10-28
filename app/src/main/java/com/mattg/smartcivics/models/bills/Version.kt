package com.mattg.smartcivics.models.bills


import com.google.gson.annotations.SerializedName

data class Version(
    @SerializedName("congressdotgov_url")
    var congressdotgovUrl: String?,
    @SerializedName("status")
    var status: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("url")
    var url: String?
)