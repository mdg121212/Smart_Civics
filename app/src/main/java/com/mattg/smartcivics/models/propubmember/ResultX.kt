package com.mattg.smartcivics.models.propubmember


import com.google.gson.annotations.SerializedName

data class ResultX(
    @SerializedName("crp_id")
    var crpId: String?,
    @SerializedName("cspan_id")
    var cspanId: String?,
    @SerializedName("current_party")
    var currentParty: String?,
    @SerializedName("date_of_birth")
    var dateOfBirth: String?,
    @SerializedName("facebook_account")
    var facebookAccount: String?,
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
    @SerializedName("in_office")
    var inOffice: Boolean?,
    @SerializedName("last_name")
    var lastName: String?,
    @SerializedName("last_updated")
    var lastUpdated: String?,
    @SerializedName("member_id")
    var memberId: String?,
    @SerializedName("middle_name")
    var middleName: String?,
    @SerializedName("most_recent_vote")
    var mostRecentVote: String?,
    @SerializedName("roles")
    var roles: List<Role>?,
    @SerializedName("rss_url")
    var rssUrl: Any?,
    @SerializedName("suffix")
    var suffix: Any?,
    @SerializedName("times_tag")
    var timesTag: String?,
    @SerializedName("times_topics_url")
    var timesTopicsUrl: String?,
    @SerializedName("twitter_account")
    var twitterAccount: String?,
    @SerializedName("url")
    var url: String?,
    @SerializedName("votesmart_id")
    var votesmartId: String?,
    @SerializedName("youtube_account")
    var youtubeAccount: Any?
)