package com.mattg.smartcivics.network

import com.mattg.smartcivics.models.opensecrets.OpenSecretGeneralResponse
import com.mattg.smartcivics.models.opensecrets.TopDonorResponse
import retrofit2.Call
import retrofit2.http.*

interface OpenSecretCall {
   // https://www.opensecrets.org/api/?method=candContrib&cid=N00007360&cycle=2020&apikey=__apikey__

    @GET("?method=candContrib&cid={cid}&cycle={cycle}&output=json&apikey={key}")
    fun getCandidateTopDonors(@Path("cid")cid: String,
                              @Path("cycle")cycle: String,
                              @Path("key")key: String): Call<TopDonorResponse>


    @GET("?method=candSummary")
    fun getCandidateBasic(@Query("cid")cid: String,
                        //  @Query("output")output: String,
                          @Query("cycle")cycle: String,
                          @Query("apiKey")key: String) : Call<OpenSecretGeneralResponse>
}