package com.mattg.smartcivics.network

import com.mattg.smartcivics.models.finance.FinanceResponse
import com.mattg.smartcivics.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface FinanceCall {

    @Headers("X-API-Key: ${Constants.financeKey}")
    @GET("{cycle}/candidates/{fec-id}.json")
    fun getMemberFinancials(@Path("cycle")year: Int, @Path("fec-id")id: String) : Call<FinanceResponse>
}