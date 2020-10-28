package com.mattg.smartcivics.models.civicapi


import com.google.gson.annotations.SerializedName

data class Office(
    @SerializedName("divisionId")
    var divisionId: String?,
    @SerializedName("levels")
    var levels: List<String>?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("officialIndices")
    var officialIndices: List<Int>?,
    @SerializedName("roles")
    var roles: List<String>?
)