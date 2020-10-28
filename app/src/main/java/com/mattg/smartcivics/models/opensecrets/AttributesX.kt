package com.mattg.smartcivics.models.opensecrets


import com.google.gson.annotations.SerializedName

data class AttributesX(
    @SerializedName("indivs")
    var indivs: String?,
    @SerializedName("org_name")
    var orgName: String?,
    @SerializedName("pacs")
    var pacs: String?,
    @SerializedName("total")
    var total: String?
)