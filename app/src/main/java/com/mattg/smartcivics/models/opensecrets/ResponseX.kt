package com.mattg.smartcivics.models.opensecrets


import com.google.gson.annotations.SerializedName

data class ResponseX(
    @SerializedName("summary")
    var summary: Summary?
)