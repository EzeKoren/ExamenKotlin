package com.yaundeCode.examenAdopcionApp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yaundeCode.examenAdopcionApp.Dog
import com.yaundecode.examenadopcionapp.R

class DogAdapter(private val dogsList : List<Dog> ) : RecyclerView.Adapter<DogViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context);
        return  DogViewHolder(layoutInflater.inflate(R.layout.item_dog, parent, false))
    }

    override fun getItemCount(): Int = dogsList.size

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val dog = dogsList[position]
        holder.render(dog)
    }
}