package com.mattg.smartcivics.models.civicapi


import com.google.gson.annotations.SerializedName

data class Official(
    @SerializedName("address")
    var address: List<Address>?,
    @SerializedName("channels")
    var channels: List<Channel>?,
    @SerializedName("emails")
    var emails: List<String>?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("party")
    var party: String?,
    @SerializedName("phones")
    var phones: List<String>?,
    @SerializedName("photoUrl")
    var photoUrl: String?,
    @SerializedName("urls")
    var urls: List<String>?
)