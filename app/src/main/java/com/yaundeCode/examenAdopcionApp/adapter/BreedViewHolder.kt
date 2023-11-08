package com.yaundeCode.examenAdopcionApp.adapter

import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.yaundeCode.examenAdopcionApp.R
import com.yaundeCode.examenAdopcionApp.models.Breed

class BreedViewHolder(breedView: View) : RecyclerView.ViewHolder(breedView){
    private val button = breedView.findViewById<Button>(R.id.breedItemButton)
    private lateinit var breed: Breed
    init {

    }

    fun render(breedModel: Breed) {
        breed = breedModel
        button.text = breed.breed
    }
}

