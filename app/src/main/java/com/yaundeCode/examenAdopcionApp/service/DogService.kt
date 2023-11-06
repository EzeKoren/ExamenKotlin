package com.yaundeCode.examenAdopcionApp.service

import com.yaundeCode.examenAdopcionApp.models.ListAllBreeds
import com.yaundeCode.examenAdopcionApp.models.RandomDogImage

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DogService {
    @GET("breeds/list/all")
    fun getAllBreeds(): Call<ListAllBreeds>

    @GET("breed/{breed}/images/random")
    fun getRandomDogImage(@Path("breed") breed: String): Call<RandomDogImage>
}


