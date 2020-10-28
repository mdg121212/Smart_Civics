package com.mattg.smartcivics.db

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mattg.smartcivics.models.propubmember.ProPublicaBaseResult
import com.mattg.smartcivics.models.civicapi.RepResponse
import com.mattg.smartcivics.models.Representative
import com.mattg.smartcivics.network.ApiCallService
import com.mattg.smartcivics.utils.generateList
import com.mattg.smartcivics.utils.mergeResultProPublica
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "RepRepository"
class RepRepository(private val apiCall: ApiCallService, private val application: Application) {
    //variables to store responses and lists
    val responseData = MutableLiveData<RepResponse>()
    val representativeList = MutableLiveData<ArrayList<Representative>>()
    val mergedRepresentativeList = MutableLiveData<ArrayList<Representative>>()


    fun getGoogleResponseAsList(address: String,  key: String, repList: ArrayList<Representative>) : ArrayList<Representative> {
        val call = apiCall.get()
        call.getRepresentatives(address,key).enqueue(object : Callback<RepResponse> {

            override fun onResponse(call: Call<RepResponse>, response: Response<RepResponse>) {
                //  Log.i(TAG, "${response.body()}")
                responseData.value = response.body()
                val result = response.body()
                //logic function located in UtilityFunctions
                generateList(result, repList)
                for(rep in repList){ // 1. empty array list should now be filled
                    Log.d(TAG, "Office: ${rep.title}, Name: ${rep.name}, Party: ${rep.party}, Address: ${rep.address}, PhotoUrl: ${rep.photo}")
                }
                representativeList.value = repList //setting live data value to list
            }

            override fun onFailure(call: Call<RepResponse>, t: Throwable) {
                Log.i(TAG, "google civic call failed: ${t.cause} ${t.message}")
            }
        })
        return repList  //this should allow viewModel to call function and receive list (pass to second api call)
    }

    //AS STRUCTURED, THIS CALL SHOULD BE CALLED SECOND AND WILL RETURN THE MERGED LIST FROM BOTH CALLS
    fun getProPublicaResponse(congressNumber: Int, type: String) {
//        progressShowing.value = true
//        recyclerMainShowing.value = false
        var returnList = representativeList.value
        val callProAllMembers = ApiCallService.getProAll()
        callProAllMembers.getAllMembers(congressNumber, type).enqueue(object: Callback<ProPublicaBaseResult>{
            override fun onResponse(
                call: Call<ProPublicaBaseResult>,
                response: Response<ProPublicaBaseResult>
            ) {
                //get response as specified type
                val result = response.body()
                //logic function located in UtilityFunctions
                val returnListMerged =
                    returnList?.let { mergeResultProPublica(result, it) } // setting return data for function
                mergedRepresentativeList.value = returnListMerged //setting live value data
//                progressShowing.value = false
//                recyclerMainShowing.value = true
            }

            override fun onFailure(call: Call<ProPublicaBaseResult>, t: Throwable) {
                Log.i(TAG, "Call failed: ${t.localizedMessage}, ${t.printStackTrace()}")
            }
        })

    }

}