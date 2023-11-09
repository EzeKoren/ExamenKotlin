package com.yaundeCode.examenAdopcionApp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yaundeCode.examenAdopcionApp.R
import com.yaundeCode.examenAdopcionApp.models.Breed

class BreedAdapter : RecyclerView.Adapter<BreedViewHolder>() {

    private var breedsList: List<Breed> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BreedViewHolder(layoutInflater.inflate(R.layout.item_breed, parent, false))
    }

    override fun getItemCount(): Int = breedsList.size

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        val breed = breedsList[position]
        holder.render(breed)
    }

    fun updateData(newBreedsList: List<Breed>) {
        breedsList = newBreedsList
        notifyDataSetChanged()
    }
}
