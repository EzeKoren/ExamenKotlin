package com.yaundecode.examenadopcionapp.fragments


import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yaundeCode.examenAdopcionApp.R
import com.yaundeCode.examenAdopcionApp.adapter.DogAdapter
import com.yaundeCode.examenAdopcionApp.models.Dog
import com.yaundeCode.examenAdopcionApp.models.ListAllBreeds
import com.yaundeCode.examenAdopcionApp.models.RandomDogImage
import com.yaundeCode.examenAdopcionApp.service.ActivityServiceApiBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Random


class DogsListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dogAdapter: DogAdapter
    private val dogList = mutableListOf<Dog>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dogs_list, container, false)
        recyclerView = view.findViewById(R.id.dogsListRecycler)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        dogAdapter = DogAdapter(dogList)
        recyclerView.adapter = dogAdapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadDogs()
    }

    private fun loadDogs() {
        val dogService = ActivityServiceApiBuilder.create()
        dogService.getAllBreeds().enqueue(object : Callback<ListAllBreeds> {
            override fun onResponse(call: Call<ListAllBreeds>, response: Response<ListAllBreeds>) {
                if (response.isSuccessful) {
                    val breeds = response.body()?.breeds
                    if (breeds != null) {
                        for ((breed, subBreeds) in breeds.entries.take(20)) {
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
                                                val favorite = random.nextBoolean()
                                                val weight = random.nextDouble() * (25 - 4) + 4
                                                val names = arrayOf("Woody", "Lito", "Pepa", "Mou", "Toto", "Rocio", "Alegria", "Firulais", "Tommy", "Roco", "Rosita", "Negro", "Gomez", "Churchill")
                                                val name = names.random()
                                                val state = if (random.nextBoolean()) "in adoption" else "adopted"
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
                                                dogAdapter.notifyDataSetChanged()
                                            }
                                        } else {
                                            Log.e(TAG, "Llamada a la API getRandomDogImage fallida con código: ${response.code()}")
                                        }
                                    }

                                    override fun onFailure(call: Call<RandomDogImage>, t: Throwable) {
                                        Log.e(TAG, "Llamada a la API getRandomDogImage fallida", t)
                                    }
                                })
                            }
                        }
                    }
                } else {
                    Log.e(TAG, "Llamada a la API getAllBreeds fallida con código: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ListAllBreeds>, t: Throwable) {
                Log.e(TAG, "Llamada a la API getAllBreeds fallida", t)
            }
        })
    }
}