package com.mattg.smartcivics.network
import com.mattg.smartcivics.models.ImageResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
interface WikiImageCall {

    @GET("api.php?action=query&format=json&prop=images")
    fun getWikiImage(@Query("titles")search: String) : Call<ImageResult>
}