package com.yaundeCode.examenAdopcionApp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.yaundeCode.examenAdopcionApp.MainActivity
import com.yaundeCode.examenAdopcionApp.R

class LoginFragment : Fragment() {

    lateinit var v: View
    private lateinit var editTextName: EditText
    private lateinit var navHeaderUsername: TextView


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_login, container, false)
        val button = v.findViewById<Button>(R.id.login_button)
        button.setOnClickListener {
            val editTextname = v.findViewById<EditText>(R.id.editTextname)
            val name = editTextname.text.toString()
            if (name.isEmpty()) {
                editTextname.setError("Este campo es obligatorio")
            } else {
                val intent = Intent(requireContext(), MainActivity::class.java)
                intent.putExtra("name", name)
                startActivity(intent)
            }
        }
        return v
    }
}
