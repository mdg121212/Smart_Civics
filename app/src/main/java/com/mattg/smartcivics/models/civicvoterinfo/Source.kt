package com.mattg.smartcivics.models.civicvoterinfo


import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("name")
    var name: String?,
    @SerializedName("official")
    var official: Boolean?
)