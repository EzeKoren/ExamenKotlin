package com.yaundeCode.examenAdopcionApp.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.yaundeCode.examenAdopcionApp.Dog
import com.yaundecode.examenadopcionapp.R

class DogViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val imagen = view.findViewById<ConstraintLayout>(R.id.item_dog)
    val guardado = view.findViewById<ImageView>(R.id.dogSaveIcon)
    val nombre = view.findViewById<TextView>(R.id.textDogNombre);
    val raza = view.findViewById<TextView>(R.id.textDogRaza)
    val subRaza = view.findViewById<TextView>(R.id.textDogSubRaza)
    val edad = view.findViewById<TextView>(R.id.textDogEdad)
    val sexo = view.findViewById<TextView>(R.id.textDogSexo)

    fun render(dogModel: Dog){
        val imagenResourceId = itemView.resources.getIdentifier(dogModel.imagen, "drawable", itemView.context.packageName)
        imagen.setBackgroundResource(imagenResourceId)
        guardado.setImageResource(if (dogModel.guardado) R.drawable.ic_icon_bookmark else R.drawable.ic_icon_bookmark_unsaved)
        nombre.text = dogModel.nombre
        raza.text = dogModel.raza
        subRaza.text = dogModel.subRaza
        edad.text = dogModel.edad.toString()
        sexo.text = dogModel.sexo
    }
}