package com.mattg.smartcivics.network

import com.mattg.smartcivics.models.propubmember.SingleProPublicaResponse
import com.mattg.smartcivics.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface SingleProPublicaMemeberCall {
    @Headers("X-API-Key: ${Constants.congressKey}")
    @GET("members/{member-id}.json") //current 116 soon 117
    fun getMember(@Path("member-id")memberid: String) : Call<SingleProPublicaResponse>
}