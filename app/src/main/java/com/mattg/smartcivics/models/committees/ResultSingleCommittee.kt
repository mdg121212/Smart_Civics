package com.mattg.smartcivics.models.committees


import com.google.gson.annotations.SerializedName

data class ResultSingleCommittee(
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
    @SerializedName("congress")
    var congress: String?,
    @SerializedName("current_members")
    var currentMembers: List<CurrentMemberXX>?,
    @SerializedName("former_members")
    var formerMembers: List<FormerMemberX>?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("num_results")
    var numResults: Int?,
    @SerializedName("ranking_member_id")
    var rankingMemberId: String?,
    @SerializedName("subcommittees")
    var subcommittees: List<SubcommitteeXX>?,
    @SerializedName("url")
    var url: String?
)