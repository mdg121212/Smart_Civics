package com.mattg.smartcivics.models


import com.google.gson.annotations.SerializedName

data class SubcommitteeX(
    @SerializedName("api_uri")
    var apiUri: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?
)