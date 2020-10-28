package com.mattg.smartcivics.models.statements


import com.google.gson.annotations.SerializedName

data class Subject(
    @SerializedName("api_uri")
    var apiUri: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("slug")
    var slug: String?
)