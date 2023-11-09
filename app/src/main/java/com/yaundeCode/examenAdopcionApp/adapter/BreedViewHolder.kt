package com.yaundeCode.examenAdopcionApp.adapter

import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.yaundeCode.examenAdopcionApp.R
import com.yaundeCode.examenAdopcionApp.models.Breed

class BreedViewHolder(breedView: View) : RecyclerView.ViewHolder(breedView){
    private val button = breedView.findViewById<Button>(R.id.breedItemButton)
    private lateinit var breed: Breed
    private val breedButton: Button = itemView.findViewById(R.id.breedItemButton)
    init {
        breedButton.setOnClickListener {
            breed.let {
                it.selected = !it.selected  // Cambia el estado de breed.selected
                render(it)  // Actualiza la vista según sea necesario
            }
        }
    }

    fun render(breedModel: Breed) {
        breed = breedModel
        button.text = breed.breed
        if(breed.selected){
            button.setBackgroundColor(ContextCompat.getColor(button.context, R.color.primary))
        } else {
            button.setBackgroundColor(ContextCompat.getColor(button.context, R.color.transparent))
        }
    }
}

