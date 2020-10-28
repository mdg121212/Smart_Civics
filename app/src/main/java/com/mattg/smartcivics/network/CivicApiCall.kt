package com.mattg.smartcivics.network

import com.mattg.smartcivics.models.civicapi.RepResponse
import com.mattg.smartcivics.models.civicvoterinfo.CivicVoterInfoResponse
import dagger.Component
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface CivicApiCall {

    @GET("representatives?")
    fun getRepresentatives(@Query("address") address: String, @Query("key")key: String)
    : Call<RepResponse>

    @GET("voterinfo?")
    fun getVoterInformationBase(@Query("address")address: String,
                                @Query("returnAllAvailableData")allData: Boolean,
                                @Query("key")key: String) : Call<CivicVoterInfoResponse>
}