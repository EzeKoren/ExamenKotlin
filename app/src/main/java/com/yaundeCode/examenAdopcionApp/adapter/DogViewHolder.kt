package com.yaundeCode.examenAdopcionApp.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yaundeCode.examenAdopcionApp.models.Dog
import com.yaundeCode.examenAdopcionApp.R



class DogViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val image = view.findViewById<ImageView>(R.id.dogImageView)
    private val saved = view.findViewById<ImageView>(R.id.dogSaveIcon)
    private val name = view.findViewById<TextView>(R.id.textDogname)
    private val breed = view.findViewById<TextView>(R.id.textDogbreed)
    private val subBreed = view.findViewById<TextView>(R.id.textDogsubBreed)
    private val age = view.findViewById<TextView>(R.id.textDogage)
    private val gender = view.findViewById<TextView>(R.id.textDoggender)

    fun render(dogModel: Dog) {
        Picasso.get().load(dogModel.image).into(image)
        val imageResourceId = itemView.resources.getIdentifier(dogModel.image, "drawable", itemView.context.packageName)
        image.setBackgroundResource(imageResourceId)
        saved.setImageResource(if (dogModel.saved) R.drawable.ic_icon_bookmark else R.drawable.ic_icon_bookmark_unsaved)
        name.text = dogModel.name
        breed.text = dogModel.breed
        subBreed.text = dogModel.subBreed.joinToString(", ") // Convertir la lista en una cadena separada por comas
        age.text = dogModel.age.toString()
        gender.text = dogModel.gender
    }
}
