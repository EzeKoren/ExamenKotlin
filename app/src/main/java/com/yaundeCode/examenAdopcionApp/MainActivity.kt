package com.yaundecode.examenadopcionapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nombre = intent.getStringExtra("nombre")
        val textViewNombre = findViewById<TextView>(R.id.textViewNombre)
        textViewNombre.text = nombre
    }
}