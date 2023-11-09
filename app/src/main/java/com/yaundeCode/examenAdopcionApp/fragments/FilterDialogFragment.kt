package com.yaundeCode.examenAdopcionApp.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment
import com.google.android.material.slider.RangeSlider
import com.yaundeCode.examenAdopcionApp.R
import com.yaundeCode.examenAdopcionApp.listener.OnFilterSelectedListener

class FilterDialogFragment(private val listener: OnFilterSelectedListener): DialogFragment() {
    private lateinit var v: View
    private var selectedGender: String? = null
    private var selectedAge: Int? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.filter_dialog, container, false)
        val radioGroup = v.findViewById<RadioGroup>(R.id.radioGroup)
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = group.findViewById<RadioButton>(checkedId)
            selectedGender = radioButton.text.toString()
        }

        val slider = v.findViewById<RangeSlider>(R.id.filterAgeSlider)
        slider.addOnChangeListener{ slider, age, fromUser ->
            selectedAge = age.toInt()
        }

        val confirmButton = v.findViewById<Button>(R.id.btnConfirmar)
        confirmButton.setOnClickListener {
            selectedGender?.let { gender ->
                listener.onFilterGenderSelected(gender)
            }
            selectedAge?.let { age ->
                listener.onFilterAgeSelected(age)
            }
            dismiss()
        }

        val cancelButton = v.findViewById<Button>(R.id.btnCancelar)
        cancelButton.setOnClickListener {
            dismiss()
        }

        return v
    }
}