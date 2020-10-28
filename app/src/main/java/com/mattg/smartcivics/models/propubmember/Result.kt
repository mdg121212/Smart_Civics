package com.mattg.smartcivics.models.propubmember


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("chamber")
    var chamber: String?,
    @SerializedName("congress")
    var congress: String?,
    @SerializedName("members")
    var members: List<Member>?,
    @SerializedName("num_results")
    var numResults: Int?,
    @SerializedName("offset")
    var offset: Int?
)