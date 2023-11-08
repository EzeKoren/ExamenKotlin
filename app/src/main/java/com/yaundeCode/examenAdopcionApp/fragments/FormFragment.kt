package com.yaundeCode.examenAdopcionApp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.yaundeAode.examenAdopcionApp.database.AppDatabase
import com.yaundeCode.examenAdopcionApp.DogsViewModel
import com.yaundeCode.examenAdopcionApp.R
import com.yaundeCode.examenAdopcionApp.database.DogDao
import com.yaundeCode.examenAdopcionApp.models.Dog
import java.util.Date

class FormFragment : Fragment() {

    private lateinit var v: View
    private var db: AppDatabase? = null
    private var dogDao: DogDao? = null
    private lateinit var dogsViewModel: DogsViewModel


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_form, container, false)
        val button = v.findViewById<Button>(R.id.saveButton)
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
            val id = 0
            val age = ageStr.toIntOrNull()
            val weight = weightStr.toDoubleOrNull()


            if (age == null || name.isEmpty() || weight == null || description.isEmpty()) {
                Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_SHORT)
                        .show()
            } else {
                /* val publishedDate = Date() */
                val dog =
                        Dog(
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
                        )
                dogsViewModel.addDog(dog)
                Toast.makeText(context, "Perro Guardado", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        return v
    }

    override fun onStart() {
        super.onStart()

        db = AppDatabase.getDatabase(v.context)

        dogDao = db?.DogDao()

        /* Probando el Room */
        /*
        cargarDB()
        showDogs()
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
