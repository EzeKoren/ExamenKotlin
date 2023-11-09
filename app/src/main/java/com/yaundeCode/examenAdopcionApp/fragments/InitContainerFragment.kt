package com.yaundeCode.examenAdopcionApp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.yaundeCode.examenAdopcionApp.R
import com.yaundeCode.examenAdopcionApp.SliderAdapter

class InitContainerFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val v = inflater.inflate(R.layout.fragment_init_container, container, false)
        val button = v.findViewById<Button>(R.id.get_started_button)
        button.setOnClickListener {
            findNavController().navigate(R.id.action_initContainerFragment3_to_loginFragment)
        }
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)
        val sliderAdapter = SliderAdapter(requireActivity())
        viewPager.adapter = sliderAdapter
    }
}
