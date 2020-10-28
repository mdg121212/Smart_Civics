package com.mattg.smartcivics.models.propubmember


import com.google.gson.annotations.SerializedName

data class Role(
    @SerializedName("at_large")
    var atLarge: Boolean?,
    @SerializedName("bills_cosponsored")
    var billsCosponsored: Int?,
    @SerializedName("bills_sponsored")
    var billsSponsored: Int?,
    @SerializedName("chamber")
    var chamber: String?,
    @SerializedName("committees")
    var committees: List<Committee>?,
    @SerializedName("congress")
    var congress: String?,
    @SerializedName("contact_form")
    var contactForm: Any?,
    @SerializedName("cook_pvi")
    var cookPvi: String?,
    @SerializedName("district")
    var district: String?,
    @SerializedName("dw_nominate")
    var dwNominate: Double?,
    @SerializedName("end_date")
    var endDate: String?,
    @SerializedName("fax")
    var fax: Any?,
    @SerializedName("fec_candidate_id")
    var fecCandidateId: String?,
    @SerializedName("ideal_point")
    var idealPoint: Any?,
    @SerializedName("leadership_role")
    var leadershipRole: Any?,
    @SerializedName("lis_id")
    var lisId: String?,
    @SerializedName("missed_votes")
    var missedVotes: Int?,
    @SerializedName("missed_votes_pct")
    var missedVotesPct: Double?,
    @SerializedName("next_election")
    var nextElection: String?,
    @SerializedName("ocd_id")
    var ocdId: String?,
    @SerializedName("office")
    var office: String?,
    @SerializedName("party")
    var party: String?,
    @SerializedName("phone")
    var phone: String?,
    @SerializedName("senate_class")
    var senateClass: String?,
    @SerializedName("seniority")
    var seniority: String?,
    @SerializedName("short_title")
    var shortTitle: String?,
    @SerializedName("start_date")
    var startDate: String?,
    @SerializedName("state")
    var state: String?,
    @SerializedName("state_rank")
    var stateRank: String?,
    @SerializedName("subcommittees")
    var subcommittees: List<Subcommittee>?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("total_present")
    var totalPresent: Int?,
    @SerializedName("total_votes")
    var totalVotes: Int?,
    @SerializedName("votes_against_party_pct")
    var votesAgainstPartyPct: Double?,
    @SerializedName("votes_with_party_pct")
    var votesWithPartyPct: Double?
)