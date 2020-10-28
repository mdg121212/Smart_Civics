package com.mattg.smartcivics.models


import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("id")
    var id: Any?,
    @SerializedName("name")
    var name: String?
)