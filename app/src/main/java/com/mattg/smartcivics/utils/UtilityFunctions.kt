package com.mattg.smartcivics.utils

import android.content.Context
import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import android.util.Log
import androidx.browser.customtabs.CustomTabsIntent
import com.mattg.smartcivics.models.*
import com.mattg.smartcivics.models.civicapi.Official
import com.mattg.smartcivics.models.civicapi.RepResponse
import com.mattg.smartcivics.models.civicvoterinfo.CivicVoterInfoResponse
import com.mattg.smartcivics.models.civicvoterinfo.appvotermodel.ElectionForDisplay
import com.mattg.smartcivics.models.propubmember.ProPublicaBaseResult
import java.util.*
import kotlin.collections.ArrayList


fun generateList(result: RepResponse?, repList: ArrayList<Representative>) {
    val reps = result?.officials
    val offices = result?.offices
    if (reps != null) {

        for (rep in reps) {
            val addressObject = rep.address?.get(0)
            //val city = rep.address?.get(0).toString()
            val city = addressObject?.city
            val address = addressObject?.line1
            val state = addressObject?.state
            val zip = addressObject?.zip
            val photoUrl = fixRepPhoto(rep)

            val stringToBreak = rep.name.toString()
            val parts = stringToBreak.split(" ")
            var firstName = ""
            var middleName = ""
            var lastName = ""
            var facebook = ""
            var twitter = ""
            var youtube = ""
            //determining if this rep has a middle initial on the civic api response, so that
            //the can be matched with the proPublica response appropriately to fetch federal level photos
            when (parts.size) {
                3 -> {
                    firstName = parts[0]
                    middleName = parts[1]
                    lastName = parts[2]
                }
                2 -> {
                    firstName = parts[0]
                    lastName = parts[1]
                    middleName = ""
                }
            }

            if(rep.channels != null){
                for (channel in rep.channels!!){
                    when(channel.type){
                        "Facebook" -> {
                            facebook = channel.id.toString()
                        }
                        "Twitter" -> {
                            twitter = channel.id.toString()
                        }
                        "YouTube" -> {
                            youtube = channel.id.toString()
                        }
                    }
                }
            }

            repList.add(
                Representative(
                    rep.name,
                    firstName,
                    middleName,
                    lastName,
                    null,
                    null,
                    city,
                    address,
                    state,
                    zip,
                    photoUrl,
                    rep.party,
                    rep.urls?.get(0),
                    rep.phones?.get(0),
                    null,
                    rep.channels,
                    facebook,
                    twitter,
                    youtube,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            )
        }
    }

    if (offices != null) {
        for (office in offices) {

            val indices = office.officialIndices

            for (index in indices!!) {
                if (office.officialIndices!!.contains(index)) {
                    repList[index].title = office.name
                    if (repList[index].title == "U.S. Senator") {
                        repList[index].photo = ""
                        repList[index].houseType = "senate"
                    }
                    if (repList[index].title == "U.S. Representative") {
                        repList[index].photo = ""
                        repList[index].houseType = "house"
                    }
                }
            }
        }
    }
}

 fun fixRepPhoto(rep: Official): String? {
    var photoUrl = rep.photoUrl
    //accounting for google using cleartext urls
    if (photoUrl != null && photoUrl.startsWith("http:")) {
        val newString = rep.photoUrl!!.removePrefix("http:")
        photoUrl = "https:$newString"
    }
    return photoUrl
}

fun addressText(rep: Representative) : String? {
    return if(!rep.address.isNullOrEmpty()){
        "${rep.address} ${rep.city}, ${rep.state} ${rep.zip}"
    } else null

}

fun getLocationReturnAddress(lat: Double, long: Double, context: Context): String {
    val addresses: List<Address>
    val geocoder = Geocoder(context, Locale.getDefault())
    addresses = geocoder.getFromLocation(lat, long, 1) // specifying a single address to return
    val address = addresses[0].getAddressLine(0)
    return  "$address "
}

fun createDisplayElection(response: CivicVoterInfoResponse): ElectionForDisplay? {
    val returnObject = ElectionForDisplay(
        response.election?.name,
        response.dropOffLocations,
        response.earlyVoteSites,
        response.pollingLocations,
        response.election?.id,
        response.election?.electionDay,
        response.state?.get(0)?.electionAdministrationBody?.absenteeVotingInfoUrl,
        response.state?.get(0)?.electionAdministrationBody?.electionInfoUrl,
        response.state?.get(0)?.electionAdministrationBody?.electionRegistrationConfirmationUrl,
        response.state?.get(0)?.electionAdministrationBody?.electionRegistrationUrl,
        response.state?.get(0)?.electionAdministrationBody?.votingLocationFinderUrl
    )
    Log.i("LISTS", "Polling: ${returnObject.dropoffLocations?.size}, Eearly: ${returnObject.earlyVoteSites?.size}")
    return returnObject
}



fun mergeResultProPublica(result: ProPublicaBaseResult?, repList: ArrayList<Representative>) : ArrayList<Representative> {

    val resultsList = result?.results
    val memberResults = resultsList?.get(0)?.members
    Log.i("MEMBERLIST", "SIZE = ${memberResults?.size}")

    val againstList = ArrayList<Double>()

    if (memberResults != null) {
        for (member in memberResults) {
            //accounts for middle name
            val memberName: String = member.firstName + " " + member.lastName
            //getting a list of voting against party percentages
            member.votesAgainstPartyPct?.let { againstList.add(it) }

            val matchedMember =
                repList.find { ((it.firstName) + " " + (it.lastName)).toLowerCase(Locale.ROOT) == memberName.toLowerCase(
                    Locale.ROOT)
                }
            Log.i("MATCH", "${matchedMember?.name}")
            matchedMember?.id = member.id
            matchedMember?.fecId = member.fecCandidateId
            matchedMember?.memberId = member.crpId
            matchedMember?.nextElection = member.nextElection
            val imageString =
                "https://raw.githubusercontent.com/unitedstates/images/gh-pages/congress/225x275/" + "${member.id}" + ".jpg"
            matchedMember?.photo = imageString
            matchedMember?.againstPary = member.votesAgainstPartyPct
        }
    }
        //getting average against party percentage
    var average = 0.0
    for (item in againstList){
        average += item
    }
     val finalAverage = average/againstList.size
    for(rep in repList){
        if(rep.againstPary != null){
            if(rep.againstPary!! > finalAverage){
                rep.againstPartyRating = (Math.round((rep.againstPary!! - finalAverage) * 100) / 100.0).toString() + " Points above average"
            } else{
                rep.againstPartyRating = (Math.round((finalAverage - rep.againstPary!!) * 100) / 100.0).toString() + " Points below average"
            }
        }

    }
        return repList
      //returns a fully formed list (good for basic info screens for all levels of representative)
      //will need to create separate call for Federal Level info
}

fun startCustomTab(url: String, context: Context) {
    val builder = CustomTabsIntent.Builder()
    val colorInt = Color.BLUE
    builder.setToolbarColor(colorInt)
    val customTabIntent = builder.build()
    customTabIntent.launchUrl(context, Uri.parse(url))
}

enum class TypeForMap {
POLLING, DROPOFF, EARLYVOTE, ADMININFO
}