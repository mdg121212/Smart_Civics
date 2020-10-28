package com.mattg.smartcivics.network


import com.mattg.smartcivics.models.ImageResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PictureCall {
    @GET("original/{bioguide}.jpg")
    fun getPicture(@Path("bioguide")id: String) : Call<ImageResult>
}