package com.mattg.smartcivics.models.civicapi


import com.google.gson.annotations.SerializedName
import com.mattg.smartcivics.models.civicapi.OcdDivisioncountryUs
import com.mattg.smartcivics.models.civicapi.OcdDivisioncountryUsstateAz

data class Divisions(
    @SerializedName("ocd-division/country:us")
    var ocdDivisioncountryUs: OcdDivisioncountryUs?,
    @SerializedName("ocd-division/country:us/state:az")
    var ocdDivisioncountryUsstateAz: OcdDivisioncountryUsstateAz?
)