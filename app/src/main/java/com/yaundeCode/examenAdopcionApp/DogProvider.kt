package com.yaundeCode.examenAdopcionApp

import android.util.Log
import com.yaundeCode.examenAdopcionApp.models.Dog
import com.yaundeCode.examenAdopcionApp.service.ActivityServiceApiBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import java.util.Random

class DogProvider {

    companion object {
        private const val TAG = "DogProvider"
        private val dogService = ActivityServiceApiBuilder.create()
        private val dogList = mutableListOf<Dog>()

        fun getDogList(): List<Dog> {
            return dogList
        }

        fun loadDogs() {
            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val breedsResponse =
                        withContext(Dispatchers.IO) { dogService.getAllBreeds().execute() }
                    if (breedsResponse.isSuccessful) {
                        val breeds = breedsResponse.body()?.breeds
                        if (breeds != null) {
                            for ((breed, subBreeds) in breeds.entries.take(20)) {
                                for (subBreed in subBreeds) {
                                    val imageResponse = withContext(Dispatchers.IO) {
                                        dogService.getRandomDogImage(breed).execute()
                                    }
                                    if (imageResponse.isSuccessful) {
                                        val imageUrl = imageResponse.body()?.imageUrl
                                        if (imageUrl != null) {
                                            val random = Random()
                                            val age = random.nextInt(10) + 1
                                            val gender =
                                                if (random.nextBoolean()) "Male" else "Female"
                                            val favorite = random.nextBoolean()
                                            val weight = random.nextDouble() * (25 - 4) + 4
                                            val names = arrayOf(
                                                "Woody",
                                                "Lito",
                                                "Pepa",
                                                "Mou",
                                                "Toto",
                                                "Rocio",
                                                "Alegria",
                                                "Firulais",
                                                "Tommy",
                                                "Roco",
                                                "Rosita",
                                                "Negro",
                                                "Gomez",
                                                "Churchill"
                                            )
                                            val name = names.random()
                                            val state =
                                                if (random.nextBoolean()) "in adoption" else "adopted"
                                            dogList.add(
                                                Dog(
                                                    saved = false,
                                                    image = imageUrl,
                                                    name = name,
                                                    breed = breed,
                                                    subBreed = subBreeds,
                                                    age = age,
                                                    gender = gender,
                                                    publishedDate = "2023_08_23",
                                                    favorite = favorite,
                                                    weight = weight,
                                                    location = "Ciudad de buenos Aires",
                                                    description = "Descripcion generica",
                                                    state = state
                                                )
                                            )
                                        }
                                    } else {
                                        Log.e(
                                            TAG,
                                            "Llamada a la API getRandomDogImage fallida con código: ${imageResponse.code()}"
                                        )
                                    }
                                }
                            }
                        }
                    } else {
                        Log.e(
                            TAG,
                            "Llamada a la API getAllBreeds fallida con código: ${breedsResponse.code()}"
                        )
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error al cargar los perros", e)
                }
            }
        }
    }
}
