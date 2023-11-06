package com.yaundecode.examenadopcionapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.yaundeCode.examenAdopcionApp.database.AppDatabase
import com.yaundeCode.examenAdopcionApp.database.DogDao
import com.yaundecode.examenadopcionapp.Dog
import com.yaundecode.examenadopcionapp.R
import java.util.Date

class FormFragment : Fragment() {

    lateinit var v : View
    private var db: AppDatabase? = null
    private var dogDao: DogDao? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_form, container, false)
        val button = v.findViewById<Button>(R.id.saveButton)
        button.setOnClickListener {
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

            val age = ageStr.toIntOrNull()
            val weight = weightStr.toIntOrNull()

            if (age == null || name.isEmpty() || weight == null || description.isEmpty()) {
                Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_SHORT)
                        .show()
            } else {
                val dog = Dog(0, name, age, gender, weight, description, breed, subBreed, location)
                println(dog)
            }
        }
        return v
    }

    override fun onStart() {
        super.onStart()

        db = AppDatabase.getAppDataBase(v.context)

        dogDao = db?.DogDao()

        cargarDB()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun cargarDB(){
        dogDao?.insertDog(Dog(1, "fede", 12, "Hola", 26, "me tengo que ir", "Tardes", "Medianoche", "Buenos Aires"))
    }


}
