package com.mattg.smartcivics.models.opensecrets


import com.google.gson.annotations.SerializedName

data class Contributor(
    @SerializedName("@attributes")
    var attributes: AttributesX?
)