package com.yaundecode.examenadopcionapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.yaundecode.examenadopcionapp.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val filterFecha = view.findViewById<RelativeLayout>(R.id.filterFecha)
        val fitlerFechaText = view.findViewById<TextView>(R.id.fitlerFechaText)
        val filterPublicacion = view.findViewById<RelativeLayout>(R.id.filterPublicacion)
        val fitlerPublicacionText = view.findViewById<TextView>(R.id.fitlerPublicacionText)
        val filterRaza = view.findViewById<RelativeLayout>(R.id.filterRaza)
        val fitlerRazaText = view.findViewById<TextView>(R.id.fitlerRazaText)

        filterFecha.setOnClickListener {
            filterFecha.setBackgroundResource(R.drawable.selector_button_background)
            fitlerFechaText.setTextColor(requireContext().resources.getColor(R.color.background))
        }
        filterFecha.setOnClickListener {
            filterPublicacion.setBackgroundResource(R.drawable.selector_button_background)
            fitlerPublicacionText.setTextColor(requireContext().resources.getColor(R.color.background))
        }
        filterFecha.setOnClickListener {
            filterRaza.setBackgroundResource(R.drawable.selector_button_background)
            fitlerRazaText.setTextColor(requireContext().resources.getColor(R.color.background))
        }
        return view
    }
}
