package com.mattg.smartcivics.models.governer


import com.google.gson.annotations.SerializedName

data class GovernerResponseItem(
    @SerializedName("address_city")
    var addressCity: String?,
    @SerializedName("address_complete")
    var addressComplete: String?,
    @SerializedName("address_number")
    var addressNumber: String?,
    @SerializedName("address_prefix")
    var addressPrefix: String?,
    @SerializedName("address_sec_unit_num")
    var addressSecUnitNum: String?,
    @SerializedName("address_sec_unit_type")
    var addressSecUnitType: String?,
    @SerializedName("address_state")
    var addressState: String?,
    @SerializedName("address_street")
    var addressStreet: String?,
    @SerializedName("address_type")
    var addressType: String?,
    @SerializedName("address_zipcode")
    var addressZipcode: String?,
    @SerializedName("biography")
    var biography: String?,
    @SerializedName("contact_page")
    var contactPage: String?,
    @SerializedName("date_of_birth")
    var dateOfBirth: String?,
    @SerializedName("entered_office")
    var enteredOffice: String?,
    @SerializedName("ethnicity")
    var ethnicity: String?,
    @SerializedName("facebook_url")
    var facebookUrl: String?,
    @SerializedName("fax")
    var fax: String?,
    @SerializedName("first_name")
    var firstName: String?,
    @SerializedName("gender")
    var gender: String?,
    @SerializedName("goes_by")
    var goesBy: String?,
    @SerializedName("last_name")
    var lastName: String?,
    @SerializedName("latitude")
    var latitude: String?,
    @SerializedName("longitude")
    var longitude: String?,
    @SerializedName("middle_name")
    var middleName: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("name_slug")
    var nameSlug: String?,
    @SerializedName("name_suffix")
    var nameSuffix: String?,
    @SerializedName("openly_lgbtq")
    var openlyLgbtq: Any?,
    @SerializedName("party")
    var party: String?,
    @SerializedName("phone")
    var phone: String?,
    @SerializedName("photo_url")
    var photoUrl: String?,
    @SerializedName("pronunciation")
    var pronunciation: String?,
    @SerializedName("religion")
    var religion: String?,
    @SerializedName("state_code")
    var stateCode: String?,
    @SerializedName("state_code_slug")
    var stateCodeSlug: String?,
    @SerializedName("state_name")
    var stateName: String?,
    @SerializedName("state_name_slug")
    var stateNameSlug: String?,
    @SerializedName("term_end")
    var termEnd: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("twitter_handle")
    var twitterHandle: String?,
    @SerializedName("twitter_url")
    var twitterUrl: String?,
    @SerializedName("votesmart")
    var votesmart: String?,
    @SerializedName("website")
    var website: String?
)