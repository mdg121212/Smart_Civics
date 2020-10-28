package com.mattg.smartcivics.models.subcommittees


import com.google.gson.annotations.SerializedName

data class ResultSubCommittee(
    @SerializedName("chair")
    var chair: String?,
    @SerializedName("chair_id")
    var chairId: String?,
    @SerializedName("chair_party")
    var chairParty: String?,
    @SerializedName("chair_state")
    var chairState: String?,
    @SerializedName("chamber")
    var chamber: String?,
    @SerializedName("committee_id")
    var committeeId: String?,
    @SerializedName("committee_name")
    var committeeName: String?,
    @SerializedName("committee_url")
    var committeeUrl: String?,
    @SerializedName("congress")
    var congress: String?,
    @SerializedName("current_members")
    var currentMembers: List<CurrentMemberX>?,
    @SerializedName("former_members")
    var formerMembers: List<Any>?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("num_results")
    var numResults: Int?,
    @SerializedName("ranking_member_id")
    var rankingMemberId: String?
)