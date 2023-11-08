package com.yaundeCode.examenAdopcionApp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.yaundeCode.examenAdopcionApp.database.AppDatabase
import com.yaundeCode.examenAdopcionApp.R
import com.yaundeCode.examenAdopcionApp.database.DogDao
import com.yaundeCode.examenAdopcionApp.models.Dog
import com.yaundeCode.examenAdopcionApp.models.ListAllBreeds
import com.yaundeCode.examenAdopcionApp.service.ActivityServiceApiBuilder.retrofit
import com.yaundeCode.examenAdopcionApp.service.DogService
import java.util.Date
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FormFragment : Fragment() {

    private lateinit var v: View
    private var db: AppDatabase? = null
    private var dogDao: DogDao? = null
    // lateinit var listDogs: MutableList<Dog>

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_form, container, false)
        val spinnerBreed = v.findViewById<Spinner>(R.id.spinnerBreed)
        val spinnerSubBreed = v.findViewById<Spinner>(R.id.spinnerSubBreed)
        val button = v.findViewById<Button>(R.id.saveButton)

        getBreedsAndSubbreeds(spinnerBreed, spinnerSubBreed)

        button.setOnClickListener {
            val image = v.findViewById<EditText>(R.id.editTextFormImage).text.toString()
            val ageStr = v.findViewById<EditText>(R.id.editTextFormAge).text.toString()
            val name = v.findViewById<EditText>(R.id.editTextFormName).text.toString()
            val spinnerGender: Spinner = v.findViewById(R.id.spinner)
            val gender = spinnerGender.selectedItem.toString()
            val weightStr = v.findViewById<EditText>(R.id.editTextFormWeight).text.toString()
            val description = v.findViewById<EditText>(R.id.editTextFormDescription).text.toString()
            val spinnerBreed: Spinner = v.findViewById(R.id.spinnerBreed)
            val breed = spinnerBreed.selectedItem.toString()
            val spinnerSubBreed: Spinner = v.findViewById(R.id.spinnerSubBreed)
            val subBreed = spinnerSubBreed.selectedItem.toString()
            val spinnerLocation: Spinner = v.findViewById(R.id.spinnerLocation)
            val location = spinnerLocation.selectedItem.toString()
            val publishedDate = Date().toString()
            val id = dogDao?.getDogCount() ?: 0
            val age = ageStr.toIntOrNull()
            val weight = weightStr.toDoubleOrNull()

            if (age == null || name.isEmpty() || weight == null || description.isEmpty()) {
                Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_SHORT)
                        .show()
            } else {
                /* val publishedDate = Date() */
                val dog =
                        Dog(
                                id + 1,
                                image,
                                name,
                                age,
                                gender,
                                publishedDate,
                                weight,
                                description,
                                breed,
                                subBreed,
                                location,
                                owner = "Martin",
                        )
                dogDao?.insertDog(dog)
                Toast.makeText(context, "Perro Guardado", Toast.LENGTH_SHORT).show()
            }
        }
        return v
    }

    override fun onStart() {
        super.onStart()

        db = AppDatabase.getAppDataBase(v.context)

        dogDao = db?.DogDao()

        /* Probando el Room */
        /*
        cargarDB()
        showDogs()
        */
    }

    private fun getBreedsAndSubbreeds(spinnerBreed: Spinner, spinnerSubBreed: Spinner) {
        val api = retrofit.create(DogService::class.java)
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
                            val subbreeds =
                                    response.body()?.breeds?.values?.flatten()
                                            ?: emptyList<String>()

                            val breedAdapter =
                                    ArrayAdapter(
                                            requireContext(),
                                            android.R.layout.simple_spinner_item,
                                            breeds
                                    )
                            breedAdapter.setDropDownViewResource(
                                    android.R.layout.simple_spinner_dropdown_item
                            )
                            spinnerBreed.adapter = breedAdapter

                            val subBreedAdapter =
                                    ArrayAdapter(
                                            requireContext(),
                                            android.R.layout.simple_spinner_item,
                                            subbreeds
                                    )
                            subBreedAdapter.setDropDownViewResource(
                                    android.R.layout.simple_spinner_dropdown_item
                            )
                            spinnerSubBreed.adapter = subBreedAdapter
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

    companion object {
        /**
         * Use this factory method to create a new instance of this fragment using the provided
         * parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FormFragment.
         */
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    /*
    fun cargarDB() {
        dogDao?.insertDog(
            Dog(
                1,
                "Fede",
                18,
                "Masculino",
                88.5f,
                "Pibe progamador",
                "Hetero",
                "Hetero2",
                "Buenos Aires"
                ))
        dogDao?.insertDog(
            Dog(
                2,
                "Facu",
                22,
                "Masculino",
                86.5f,
                "Buenos dias",
                "Hetero",
                "Hetero2",
                "Buenos Aires"
            ))
    }

    fun showDogs() {
        CoroutineScope(Dispatchers.Main).launch {
            var listDogs = dogDao?.getAll()
            listDogs?.forEach { dog ->
                Log.d("Dog", dog.toString())
            }
        }
    }
    */
}
