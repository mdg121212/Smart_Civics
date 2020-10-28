package com.mattg.smartcivics.models.committees


import com.google.gson.annotations.SerializedName

data class SubcommitteeXX(
    @SerializedName("api_uri")
    var apiUri: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?
)