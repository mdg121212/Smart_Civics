package com.mattg.smartcivics.models.propubmember


import com.google.gson.annotations.SerializedName

data class ProPublicaMember(
    @SerializedName("api_uri")
    var apiUri: String?,
    @SerializedName("contact_form")
    var contactForm: String?,
    @SerializedName("cook_pvi")
    var cookPvi: Any?,
    @SerializedName("crp_id")
    var crpId: String?,
    @SerializedName("cspan_id")
    var cspanId: String?,
    @SerializedName("date_of_birth")
    var dateOfBirth: String?,
    @SerializedName("dw_nominate")
    var dwNominate: Double?,
    @SerializedName("facebook_account")
    var facebookAccount: String?,
    @SerializedName("fax")
    var fax: Any?,
    @SerializedName("fec_candidate_id")
    var fecCandidateId: String?,
    @SerializedName("first_name")
    var firstName: String?,
    @SerializedName("gender")
    var gender: String?,
    @SerializedName("google_entity_id")
    var googleEntityId: String?,
    @SerializedName("govtrack_id")
    var govtrackId: String?,
    @SerializedName("icpsr_id")
    var icpsrId: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("ideal_point")
    var idealPoint: Any?,
    @SerializedName("in_office")
    var inOffice: Boolean?,
    @SerializedName("last_name")
    var lastName: String?,
    @SerializedName("last_updated")
    var lastUpdated: String?,
    @SerializedName("leadership_role")
    var leadershipRole: Any?,
    @SerializedName("lis_id")
    var lisId: String?,
    @SerializedName("middle_name")
    var middleName: Any?,
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
    @SerializedName("rss_url")
    var rssUrl: String?,
    @SerializedName("senate_class")
    var senateClass: String?,
    @SerializedName("seniority")
    var seniority: String?,
    @SerializedName("short_title")
    var shortTitle: String?,
    @SerializedName("state")
    var state: String?,
    @SerializedName("state_rank")
    var stateRank: String?,
    @SerializedName("suffix")
    var suffix: Any?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("total_present")
    var totalPresent: Int?,
    @SerializedName("total_votes")
    var totalVotes: Int?,
    @SerializedName("twitter_account")
    var twitterAccount: Any?,
    @SerializedName("url")
    var url: String?,
    @SerializedName("votes_against_party_pct")
    var votesAgainstPartyPct: Double?,
    @SerializedName("votes_with_party_pct")
    var votesWithPartyPct: Double?,
    @SerializedName("votesmart_id")
    var votesmartId: String?,
    @SerializedName("youtube_account")
    var youtubeAccount: String?
)