package com.yaundeCode.examenAdopcionApp

import android.app.Application
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yaundeAode.examenAdopcionApp.database.AppDatabase
import com.yaundeCode.examenAdopcionApp.database.DogDao
import com.yaundeCode.examenAdopcionApp.models.Dog
import com.yaundeCode.examenAdopcionApp.models.ListAllBreeds
import com.yaundeCode.examenAdopcionApp.models.RandomDogImage
import com.yaundeCode.examenAdopcionApp.service.ActivityServiceApiBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Date
import java.util.Random

class DogsViewModel(application: Application) : AndroidViewModel(application) {
    val dogList: MutableLiveData<List<Dog>> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    private val db: AppDatabase = AppDatabase.getDatabase(application)
    private val dogDao: DogDao = db.DogDao()

    fun loadDogs() {
        if(dogDao?.getDogCount() == 0) {
            loadDogsFromApi()
        } else {
            loadDogsFromRoomDB()
        }
    }

    fun addDog(dog: Dog) {
        dogDao.insertDog(dog)
        loadDogsFromRoomDB()
    }

    private fun loadDogsFromApi() {
        val newDogList = mutableListOf<Dog>()
        val dogService = ActivityServiceApiBuilder.create()
        dogService.getAllBreeds().enqueue(object : Callback<ListAllBreeds> {
            override fun onResponse(call: Call<ListAllBreeds>, response: Response<ListAllBreeds>) {
                if (response.isSuccessful) {
                    val breeds = response.body()?.breeds
                    if (breeds != null) {
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
                                                val owners = arrayOf("Martin", "refugio patitas", "firuHome")
                                                val owner = owners.random()
                                                val status = random.nextBoolean()
                                                val favorite = random.nextBoolean()

                                                var newDog = Dog(
                                                    image = imageUrl,
                                                    name = name,
                                                    age = age,
                                                    gender = gender,
                                                    publishedDate = publishedDate,
                                                    weight = weight,
                                                    description = "Descripcion generica",
                                                    breed = breed,
                                                    subBreed = subBreeds.shuffled()[0],
                                                    location = "Ciudad de buenos Aires",
                                                    owner = owner,
                                                    status = status,
                                                    favorite = favorite
                                                )

                                                newDogList.add(newDog)
                                                dogList.value = newDogList
                                                dogDao.insertDog(newDog)
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

    private fun loadDogsFromRoomDB() {
        val dogListDB = dogDao.getAll()
        dogList.value = dogListDB
    }
}
