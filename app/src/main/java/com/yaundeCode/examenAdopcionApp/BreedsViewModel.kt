package com.yaundeCode.examenAdopcionApp


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yaundeCode.examenAdopcionApp.models.Breed
import com.yaundeCode.examenAdopcionApp.models.ListAllBreeds
import com.yaundeCode.examenAdopcionApp.service.ActivityServiceApiBuilder
import com.yaundeCode.examenAdopcionApp.service.DogService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BreedsViewModel : ViewModel() {
    val breedList: MutableLiveData<List<Breed>> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    fun getBreeds() {

        val newBreedList = mutableListOf<Breed>()

        val api = ActivityServiceApiBuilder.retrofit.create(DogService::class.java)
        val call: Call<ListAllBreeds> = api.getAllBreeds()

        call.enqueue(
            object : Callback<ListAllBreeds> {
                override fun onResponse(
                    call: Call<ListAllBreeds>,
                    response: Response<ListAllBreeds>
                ) {
                    if (response.isSuccessful) {
                        println(response.body())
                        val breeds =
                            response.body()?.breeds?.keys?.toList() ?: emptyList<String>()
                        breeds.forEach { breed ->
                                newBreedList.add(
                                    Breed(
                                        breed = breed
                                    )
                                )
                        }
                        breedList.value = newBreedList
                    } else {
                        println("Error: ${response.errorBody()}")
                    }
                }

                override fun onFailure(call: Call<ListAllBreeds>, t: Throwable) {
                    println("Error: ${t.message}")
                }
            }
        )
    }
}