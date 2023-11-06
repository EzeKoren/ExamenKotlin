package com.yaundecode.examenadopcionapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.yaundecode.examenadopcionapp.R

class GetStartedButtonFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_get_started_button, container, false)
        val button = view.findViewById<Button>(R.id.get_started_button)

        button.setOnClickListener {
            //
            // findNavController().navigate(R.id.action_getStartedButtonFragment_to_loginButtonFragment)

        }
        return view
    }
}
