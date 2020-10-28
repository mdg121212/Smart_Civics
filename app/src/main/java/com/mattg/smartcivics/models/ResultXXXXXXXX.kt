package com.mattg.smartcivics.models


import com.google.gson.annotations.SerializedName

data class ResultXXXXXXXX(
    @SerializedName("amount")
    var amount: Double?,
    @SerializedName("category")
    var category: String?,
    @SerializedName("category_slug")
    var categorySlug: String?,
    @SerializedName("change_from_previous_quarter")
    var changeFromPreviousQuarter: Double?,
    @SerializedName("year_to_date")
    var yearToDate: Double?
)