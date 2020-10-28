package com.mattg.smartcivics.models.opensecrets


import com.google.gson.annotations.SerializedName

data class TopDonorResponse(
    @SerializedName("response")
    var response: Response?
)