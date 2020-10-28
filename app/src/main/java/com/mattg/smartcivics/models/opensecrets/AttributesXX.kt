package com.mattg.smartcivics.models.opensecrets


import com.google.gson.annotations.SerializedName

data class AttributesXX(
    @SerializedName("cand_name")
    var candName: String?,
    @SerializedName("cash_on_hand")
    var cashOnHand: String?,
    @SerializedName("chamber")
    var chamber: String?,
    @SerializedName("cid")
    var cid: String?,
    @SerializedName("cycle")
    var cycle: String?,
    @SerializedName("debt")
    var debt: String?,
    @SerializedName("first_elected")
    var firstElected: String?,
    @SerializedName("last_updated")
    var lastUpdated: String?,
    @SerializedName("next_election")
    var nextElection: String?,
    @SerializedName("origin")
    var origin: String?,
    @SerializedName("party")
    var party: String?,
    @SerializedName("source")
    var source: String?,
    @SerializedName("spent")
    var spent: String?,
    @SerializedName("state")
    var state: String?,
    @SerializedName("total")
    var total: String?
)