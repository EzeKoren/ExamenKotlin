package com.yaundeCode.examenAdopcionApp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yaundeCode.examenAdopcionApp.MainActivity
import com.yaundeCode.examenAdopcionApp.R
import com.yaundeCode.examenAdopcionApp.models.Dog

class DogAdapter: RecyclerView.Adapter<DogViewHolder>() {

    private var dogsList: List<Dog> = emptyList()
    private var favoritesCount: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DogViewHolder(layoutInflater.inflate(R.layout.item_dog, parent, false))
    }

    override fun getItemCount(): Int = dogsList.size

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val dog = dogsList[position]
        holder.render(dog)
    }

    fun updateData(newDogsList: List<Dog>) {
        dogsList = newDogsList
        notifyDataSetChanged()
        //updateFavoritesBadge()
    }

    /*fun updateFavoritesBadge() {
        favoritesCount = dogsList.count { it.favorite }
        favoritesBadgeTextView.text = favoritesCount.toString()
    }

     */
}
