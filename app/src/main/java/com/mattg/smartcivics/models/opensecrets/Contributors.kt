package com.mattg.smartcivics.models.opensecrets


import com.google.gson.annotations.SerializedName

data class Contributors(
    @SerializedName("@attributes")
    var attributes: Attributes?,
    @SerializedName("contributor")
    var contributor: List<Contributor>?
)