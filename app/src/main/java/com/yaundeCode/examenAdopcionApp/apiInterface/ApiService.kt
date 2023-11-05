package com.yaundeCode.examenAdopcionApp.apiInterface

import com.yaundeCode.examenAdopcionApp.models.DogsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getDogs(@Url url:String): Response<DogsResponse>
}