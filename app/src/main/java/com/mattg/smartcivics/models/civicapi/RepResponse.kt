package com.mattg.smartcivics.models.civicapi


import com.google.gson.annotations.SerializedName

data class RepResponse(
    @SerializedName("divisions")
    var divisions: Divisions?,
    @SerializedName("kind")
    var kind: String?,
    @SerializedName("normalizedInput")
    var normalizedInput: NormalizedInput?,
    @SerializedName("offices")
    var offices: List<Office>?,
    @SerializedName("officials")
    var officials: List<Official>?
)