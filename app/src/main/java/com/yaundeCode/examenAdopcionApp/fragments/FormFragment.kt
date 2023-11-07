package com.yaundeCode.examenAdopcionApp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.yaundeCode.examenAdopcionApp.R
import com.yaundeCode.examenAdopcionApp.models.Dog
import com.yaundeCode.examenAdopcionApp.models.ListAllBreeds
import com.yaundeCode.examenAdopcionApp.service.ActivityServiceApiBuilder.retrofit
import com.yaundeCode.examenAdopcionApp.service.DogService
import java.util.Date
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response
import android.widget.ArrayAdapter


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass. Use the [FormFragment.newInstance] factory method to create an
 * instance of this fragment.
 */
class FormFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_form, container, false)
        val spinnerBreed = v.findViewById<Spinner>(R.id.spinnerBreed)
        val spinnerSubBreed = v.findViewById<Spinner>(R.id.spinnerSubBreed)
        val button = v.findViewById<Button>(R.id.form_button)
        

        getBreedsAndSubbreeds(spinnerBreed, spinnerSubBreed)


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
            val weight = weightStr.toDoubleOrNull()

            if (age == null || name.isEmpty() || weight == null || description.isEmpty()) {
                Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_SHORT)
                        .show()
            } else {
                val publishedDate = Date()
                // TODO: Traer una imagen random de la API
                val dog =
                        Dog(
                                "",
                                name,
                                breed,
                                subBreed,
                                age,
                                gender,
                                publishedDate,
                                weight,
                                location,
                                description
                        )
                println(dog)
            }
        }
        return v
    }



    private fun getBreedsAndSubbreeds(spinnerBreed: Spinner, spinnerSubBreed: Spinner) {
        val api = retrofit.create(DogService::class.java)
        val call: Call<ListAllBreeds> = api.getAllBreeds()

        call.enqueue(object : Callback<ListAllBreeds> {
            override fun onResponse(call: Call<ListAllBreeds>, response: Response<ListAllBreeds>) {
                if (response.isSuccessful) {
                    println(response.body())
                    val breeds = response.body()?.breeds?.keys?.toList() ?: emptyList<String>()
                    val subbreeds = response.body()?.breeds?.values?.flatten() ?: emptyList<String>()

                    val breedAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, breeds)
                    breedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerBreed.adapter = breedAdapter

                    val subBreedAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, subbreeds)
                    subBreedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerSubBreed.adapter = subBreedAdapter
                } else {
                    println("Error: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<ListAllBreeds>, t: Throwable) {
                println("Error: ${t.message}")
            }
        })
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
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                FormFragment().apply {
                    arguments =
                            Bundle().apply {
                                putString(ARG_PARAM1, param1)
                                putString(ARG_PARAM2, param2)
                            }
                }
    }
}
