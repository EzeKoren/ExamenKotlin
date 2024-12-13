package com.yaundeCode.examenAdopcionApp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.yaundeCode.examenAdopcionApp.R
import com.yaundeCode.examenAdopcionApp.models.Breed

class BreedAdapter : RecyclerView.Adapter<BreedViewHolder>() {

    private var breedsList: List<Breed> = emptyList()
    private lateinit var breedViewHolder: BreedViewHolder
    var selectedBreeds: MutableLiveData<List<String>> = MutableLiveData(emptyList())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        breedViewHolder = BreedViewHolder(layoutInflater.inflate(R.layout.item_breed, parent, false), ::addToFilter)

        return breedViewHolder
    }

    override fun getItemCount(): Int = breedsList.size

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        val breed = breedsList[position]
        holder.render(breed)
    }

    private fun addToFilter(breed: String, selected: Boolean) {
        var newList: MutableList<String> = mutableListOf()
        newList.addAll(selectedBreeds.value!!)

        if (selected) newList.add(breed)
        else if (newList.contains(breed)) newList.remove(breed)

        selectedBreeds.value = newList
    }

    fun updateData(newBreedsList: List<Breed>) {
        breedsList = newBreedsList
        notifyDataSetChanged()
    }
}

