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
import com.yaundecode.examenadopcionapp.Dog
import com.yaundecode.examenadopcionapp.R

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
        val button = v.findViewById<Button>(R.id.form_button)
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
            val weight = weightStr.toFloatOrNull()

            if (age == null || name.isEmpty() || weight == null || description.isEmpty()) {
                Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_SHORT)
                        .show()
            } else {
                val dog = Dog(name, age, gender, weight, description, breed, subBreed, location)
                println(dog)
            }
        }
        return v
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
