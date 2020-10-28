package com.mattg.smartcivics.models.opensecrets


import com.google.gson.annotations.SerializedName

data class OpenSecretGeneralResponse(
    @SerializedName("response")
    var response: ResponseX?
)