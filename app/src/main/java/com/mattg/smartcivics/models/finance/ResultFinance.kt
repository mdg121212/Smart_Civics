package com.mattg.smartcivics.models.finance


import com.google.gson.annotations.SerializedName

data class
ResultFinance(
    @SerializedName("begin_cash")
    var beginCash: Double?,
    @SerializedName("candidate_loans")
    var candidateLoans: Double?,
    @SerializedName("committee")
    var committee: String?,
    @SerializedName("coordinated_expenditures")
    var coordinatedExpenditures: Double?,
    @SerializedName("date_coverage_from")
    var dateCoverageFrom: String?,
    @SerializedName("date_coverage_to")
    var dateCoverageTo: String?,
    @SerializedName("debts_owed")
    var debtsOwed: Double?,
    @SerializedName("display_name")
    var displayName: String?,
    @SerializedName("district")
    var district: Any?,
    @SerializedName("end_cash")
    var endCash: Double?,
    @SerializedName("facebook_url")
    var facebookUrl: String?,
    @SerializedName("fec_uri")
    var fecUri: String?,
    @SerializedName("gender")
    var gender: String?,
    @SerializedName("google_id")
    var googleId: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("independent_expenditures")
    var independentExpenditures: Double?,
    @SerializedName("individual_refunds")
    var individualRefunds: Double?,
    @SerializedName("mailing_address")
    var mailingAddress: String?,
    @SerializedName("mailing_city")
    var mailingCity: String?,
    @SerializedName("mailing_state")
    var mailingState: String?,
    @SerializedName("mailing_zip")
    var mailingZip: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("other_cycles")
    var otherCycles: List<Int>?,
    @SerializedName("pac_refunds")
    var pacRefunds: Double?,
    @SerializedName("party")
    var party: String?,
    @SerializedName("percent_unitemized")
    var percentUnitemized: Double?,
    @SerializedName("state")
    var state: Any?,
    @SerializedName("status")
    var status: String?,
    @SerializedName("total_contributions")
    var totalContributions: Double?,
    @SerializedName("total_disbursements")
    var totalDisbursements: Double?,
    @SerializedName("total_from_individuals")
    var totalFromIndividuals: Double?,
    @SerializedName("total_from_individuals_itemized")
    var totalFromIndividualsItemized: Double?,
    @SerializedName("total_from_individuals_unitemized")
    var totalFromIndividualsUnitemized: Double?,
    @SerializedName("total_from_pacs")
    var totalFromPacs: Double?,
    @SerializedName("total_receipts")
    var totalReceipts: Double?,
    @SerializedName("total_refunds")
    var totalRefunds: Double?,
    @SerializedName("transfers_in")
    var transfersIn: Double?,
    @SerializedName("twitter_user")
    var twitterUser: String?,
    @SerializedName("url")
    var url: String?
)