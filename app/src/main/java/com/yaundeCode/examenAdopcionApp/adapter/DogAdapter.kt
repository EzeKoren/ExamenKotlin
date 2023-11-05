package com.yaundeCode.examenAdopcionApp.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yaundeCode.examenAdopcionApp.Dog

class DogAdapter(val dogsList : List<Dog> ) : RecyclerView.Adapter<DogViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
    }

    override fun getItemCount(): Int = dogsList.size

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
    }
}