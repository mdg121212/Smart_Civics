package com.mattg.smartcivics.models


import com.google.gson.annotations.SerializedName

data class ResultMemberFundedTravel(
    @SerializedName("chamber")
    var chamber: String?,
    @SerializedName("congress")
    var congress: Int?,
    @SerializedName("departure_date")
    var departureDate: String?,
    @SerializedName("destination")
    var destination: String?,
    @SerializedName("document_id")
    var documentId: String?,
    @SerializedName("filing_type")
    var filingType: String?,
    @SerializedName("is_member")
    var isMember: Int?,
    @SerializedName("pdf_url")
    var pdfUrl: String?,
    @SerializedName("return_date")
    var returnDate: String?,
    @SerializedName("sponsor")
    var sponsor: String?,
    @SerializedName("traveler")
    var traveler: String?
)