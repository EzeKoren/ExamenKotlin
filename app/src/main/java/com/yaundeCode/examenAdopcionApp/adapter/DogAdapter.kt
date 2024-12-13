package com.yaundeCode.examenAdopcionApp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yaundeCode.examenAdopcionApp.R
import com.yaundeCode.examenAdopcionApp.models.Dog

class DogAdapter(val username: String) : RecyclerView.Adapter<DogViewHolder>() {

    private var dogsList: List<Dog> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        var dogViewHolder = DogViewHolder(layoutInflater.inflate(R.layout.item_dog, parent, false), username = username)

        return dogViewHolder
    }

    override fun getItemCount(): Int = dogsList.size

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val dog = dogsList[position]
        holder.render(dog)
    }

    fun updateData(newDogsList: List<Dog>) {
        dogsList = newDogsList
        notifyDataSetChanged()
    }

}
