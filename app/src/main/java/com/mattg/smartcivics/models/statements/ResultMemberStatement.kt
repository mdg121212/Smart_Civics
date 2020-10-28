package com.mattg.smartcivics.models.statements


import com.google.gson.annotations.SerializedName

data class ResultMemberStatement(
    @SerializedName("chamber")
    var chamber: String?,
    @SerializedName("date")
    var date: String?,
    @SerializedName("party")
    var party: String?,
    @SerializedName("state")
    var state: String?,
    @SerializedName("statement_type")
    var statementType: String?,
    @SerializedName("subjects")
    var subjects: List<Subject>?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("url")
    var url: String?
)