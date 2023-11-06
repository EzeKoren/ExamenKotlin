package com.yaundecode.examenadopcionapp.service
import com.yaundecode.examenadopcionapp.models.RandomDogImage
import com.yaundecode.examenadopcionapp.models.ListAllBreeds

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DogService {
    @GET("breeds/list/all")
    fun getAllBreeds(): Call<ListAllBreeds>

    @GET("breed/{breed}/images/random")
    fun getRandomDogImage(@Path("breed") breed: String): Call<RandomDogImage>
}


