package com.mattg.smartcivics.models.opensecrets


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("contributors")
    var contributors: Contributors?
)