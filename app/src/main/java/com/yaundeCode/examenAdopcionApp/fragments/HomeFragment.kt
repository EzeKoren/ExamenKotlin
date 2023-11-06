package com.yaundecode.examenadopcionapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.yaundecode.examenadopcionapp.R

class HomeFragment : Fragment() {

    private lateinit var filterFecha: AppCompatButton
    private lateinit var filterUbicacion: AppCompatButton
    private lateinit var filterRaza: AppCompatButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        filterFecha = view.findViewById(R.id.filterFecha)
        filterUbicacion = view.findViewById(R.id.filterUbicacion)
        filterRaza = view.findViewById(R.id.filterRaza)

        setToggleOnClickListener(filterFecha)
        setToggleOnClickListener(filterUbicacion)
        setToggleOnClickListener(filterRaza)

        return view
    }

    private fun setToggleOnClickListener(button: AppCompatButton) {
        button.setOnClickListener {
            resetButtons() // Restablece el estado de todos los botones

            button.isSelected = true // Selecciona el botón actual
            button.setBackgroundResource(R.drawable.button_background_press)
            button.setTextColor(requireContext().resources.getColor(R.color.background))
        }
        /*
        button.setOnClickListener {
            button.isSelected = !button.isSelected
            if (button.isSelected) {
                button.setBackgroundResource(R.drawable.button_background_press)
                button.setTextColor(requireContext().resources.getColor(R.color.background))
            } else {
                button.setBackgroundResource(R.drawable.button_background_nopress)
                button.setTextColor(requireContext().resources.getColor(R.color.text))
            }
        }
        */
    }
    private fun resetButtons() {
        filterFecha.isSelected = false
        filterFecha.setBackgroundResource(R.drawable.button_background_nopress)
        filterFecha.setTextColor(requireContext().resources.getColor(R.color.text))

        filterUbicacion.isSelected = false
        filterUbicacion.setBackgroundResource(R.drawable.button_background_nopress)
        filterUbicacion.setTextColor(requireContext().resources.getColor(R.color.text))

        filterRaza.isSelected = false
        filterRaza.setBackgroundResource(R.drawable.button_background_nopress)
        filterRaza.setTextColor(requireContext().resources.getColor(R.color.text))
    }

}
