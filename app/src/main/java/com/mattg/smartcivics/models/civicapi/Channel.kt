package com.mattg.smartcivics.models.civicapi


import com.google.gson.annotations.SerializedName

data class Channel(
    @SerializedName("id")
    var id: String?,
    @SerializedName("type")
    var type: String?
)