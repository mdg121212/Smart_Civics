package com.mattg.smartcivics.models.bills


import com.google.gson.annotations.SerializedName

data class BillX(
    @SerializedName("active")
    var active: Boolean?,
    @SerializedName("bill_id")
    var billId: String?,
    @SerializedName("bill_type")
    var billType: String?,
    @SerializedName("bill_uri")
    var billUri: String?,
    @SerializedName("committees")
    var committees: String?,
    @SerializedName("congress")
    var congress: String?,
    @SerializedName("congressdotgov_url")
    var congressdotgovUrl: String?,
    @SerializedName("cosponsors")
    var cosponsors: Int?,
    @SerializedName("cosponsors_by_party")
    var cosponsorsByParty: CosponsorsByParty?,
    @SerializedName("enacted")
    var enacted: Any?,
    @SerializedName("govtrack_url")
    var govtrackUrl: String?,
    @SerializedName("gpo_pdf_uri")
    var gpoPdfUri: Any?,
    @SerializedName("house_passage")
    var housePassage: Any?,
    @SerializedName("introduced_date")
    var introducedDate: String?,
    @SerializedName("last_vote")
    var lastVote: Any?,
    @SerializedName("latest_major_action")
    var latestMajorAction: String?,
    @SerializedName("latest_major_action_date")
    var latestMajorActionDate: String?,
    @SerializedName("number")
    var number: String?,
    @SerializedName("primary_subject")
    var primarySubject: String?,
    @SerializedName("senate_passage")
    var senatePassage: Any?,
    @SerializedName("short_title")
    var shortTitle: String?,
    @SerializedName("sponsor_id")
    var sponsorId: String?,
    @SerializedName("sponsor_name")
    var sponsorName: String?,
    @SerializedName("sponsor_party")
    var sponsorParty: String?,
    @SerializedName("sponsor_state")
    var sponsorState: String?,
    @SerializedName("sponsor_title")
    var sponsorTitle: String?,
    @SerializedName("sponsor_uri")
    var sponsorUri: String?,
    @SerializedName("summary")
    var summary: String?,
    @SerializedName("summary_short")
    var summaryShort: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("vetoed")
    var vetoed: Any?
)