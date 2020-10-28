package com.mattg.smartcivics.network

import com.mattg.smartcivics.models.news.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiCall {

    @GET("everything?q=politics")
    fun getBasicPoliticsNews(@Query("apiKey") key: String) : Call<NewsResponse>

    @GET("everything?q=senate")
    fun getSenateNews(@Query("apiKey") key: String) : Call<NewsResponse>

    @GET("everything?q=congress")
    fun getCongressNews(@Query("apiKey") key: String) : Call<NewsResponse>
}