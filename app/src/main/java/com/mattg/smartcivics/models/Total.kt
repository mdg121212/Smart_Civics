package com.mattg.smartcivics.models


import com.google.gson.annotations.SerializedName

data class Total(
    @SerializedName("no")
    var no: Int?,
    @SerializedName("not_voting")
    var notVoting: Int?,
    @SerializedName("present")
    var present: Int?,
    @SerializedName("yes")
    var yes: Int?
)