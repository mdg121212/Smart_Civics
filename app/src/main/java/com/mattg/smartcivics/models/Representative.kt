package com.mattg.smartcivics.models

import com.google.gson.annotations.SerializedName
import com.mattg.smartcivics.models.civicapi.Channel


data class Representative (
    @SerializedName("name")
    val name: String?,
    @SerializedName("firstName")
    var firstName: String?,
    @SerializedName("middleName")
    var middleName: String?,
    @SerializedName("lastName")
    var lastName: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("houseType")
    var houseType: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("address")
    var address: String?,
    @SerializedName("state")
    val state: String?,
    @SerializedName("zip")
    val zip: String?,
    @SerializedName("photo")
    var photo: String?,
    @SerializedName("party")
    val party: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("channels")
    var channels: List<Channel>?,
    @SerializedName("facebook")
    var facebook: String?,
    @SerializedName("twitter")
    var twitter: String?,
    @SerializedName("youtube")
    var youtube: String?,
    @SerializedName("fecId")
    var fecId: String?,
    @SerializedName("memberId")
    var memberId: String?,
    @SerializedName("nextElection")
    var nextElection: String?,
    @SerializedName("againstParty")
    var againstPary: Double?,
    @SerializedName("againstPartyRating")
    var againstPartyRating: String?
    )