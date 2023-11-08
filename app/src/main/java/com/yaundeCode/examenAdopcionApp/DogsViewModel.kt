package com.yaundeCode.examenAdopcionApp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yaundeCode.examenAdopcionApp.models.Dog
import com.yaundeCode.examenAdopcionApp.models.ListAllBreeds
import com.yaundeCode.examenAdopcionApp.models.RandomDogImage
import com.yaundeCode.examenAdopcionApp.service.ActivityServiceApiBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Date
import java.util.Random

class DogsViewModel : ViewModel() {
    val dogList: MutableLiveData<List<Dog>> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()


    fun loadDogs() {
        val id = 0
        val dogService = ActivityServiceApiBuilder.create()
        dogService.getAllBreeds().enqueue(object : Callback<ListAllBreeds> {
            override fun onResponse(call: Call<ListAllBreeds>, response: Response<ListAllBreeds>) {
                if (response.isSuccessful) {
                    val breeds = response.body()?.breeds
                    if (breeds != null) {

                        val newDogList = mutableListOf<Dog>()


                        for ((breed, subBreeds) in breeds.entries.shuffled().take(20)) {
                            for (subBreed in subBreeds) {
                                dogService.getRandomDogImage(breed).enqueue(object :
                                    Callback<RandomDogImage> {
                                    override fun onResponse(call: Call<RandomDogImage>, response: Response<RandomDogImage>) {
                                        if (response.isSuccessful) {
                                            val imageUrl = response.body()?.imageUrl
                                            if (imageUrl != null) {
                                                val random = Random()
                                                val age = random.nextInt(10) + 1
                                                val gender = if (random.nextBoolean()) "Male" else "Female"
                                                val weight = random.nextDouble() * (25 - 4) + 4
                                                val names = arrayOf("Woody", "Lito", "Pepa", "Mou", "Toto", "Rocio", "Alegria", "Firulais", "Tommy", "Roco", "Rosita", "Negro", "Gomez", "Churchill")
                                                val name = names.random()
                                                val publishedDate = Date().toString()
                                                newDogList.add(
                                                    Dog(
                                                        id = id,
                                                        image = imageUrl,
                                                        name = name,
                                                        age = age,
                                                        gender = gender,
                                                        publishedDate = publishedDate,
                                                        weight = weight,
                                                        description = "Descripcion generica",
                                                        breed = breed,
                                                        subBreed = subBreeds.shuffled()[0],
                                                        location = "Ciudad de buenos Aires",)
                                                )
                                                dogList.value = newDogList
                                                id + 1
                                            }
                                        } else {
                                            errorMessage.value = "Error al obtener todas las razas: ${response.errorBody()}"
                                        }
                                    }

                                    override fun onFailure(call: Call<RandomDogImage>, t: Throwable) {
                                        errorMessage.value = "Fallo al obtener todas las razas: ${t.message}"
                                    }
                                })
                            }
                        }
                    }
                } else {
                    errorMessage.value = "Error al obtener todas las razas: ${response.errorBody()}"
                }
            }

            override fun onFailure(call: Call<ListAllBreeds>, t: Throwable) {
                errorMessage.value = "Fallo al obtener todas las razas: ${t.message}"
            }
        })
    }
}
