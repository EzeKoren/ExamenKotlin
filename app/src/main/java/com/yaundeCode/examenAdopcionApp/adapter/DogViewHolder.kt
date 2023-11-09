package com.yaundeCode.examenAdopcionApp.adapter

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.gson.Gson
import com.yaundeCode.examenAdopcionApp.R
import com.yaundeCode.examenAdopcionApp.database.AppDatabase
import com.yaundeCode.examenAdopcionApp.models.Dog

class DogViewHolder(dogView: View) : RecyclerView.ViewHolder(dogView) {

    private val image = dogView.findViewById<ImageView>(R.id.dogImageView)
    private val shimmer = dogView.findViewById<ShimmerFrameLayout>(R.id.dogImageShimmer)
    private val saved = dogView.findViewById<ImageView>(R.id.dogSaveIcon)
    private val name = dogView.findViewById<TextView>(R.id.textDogName)
    private val breed = dogView.findViewById<TextView>(R.id.textDogBreed)
    private val subBreed = dogView.findViewById<TextView>(R.id.textDogSubBreed)
    private val stats = dogView.findViewById<TextView>(R.id.textDogStats)

    private lateinit var dog: Dog

    init {
        itemView.setOnClickListener {
            val dogJsonString = Gson().toJson(dog).toString()

            val bundle = bundleOf("dog" to dogJsonString)
            it.findNavController().navigate(R.id.action_dogsListFragment_to_dogDetailFragment, bundle)
        }
    }

    fun render(dogModel: Dog) {
        dog = dogModel

        shimmer.startShimmer()

        Glide.with(itemView)
                .load(dogModel.image)
                .listener(
                        object : RequestListener<Drawable> {
                            override fun onResourceReady(
                                    resource: Drawable?,
                                    model: Any?,
                                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                                    dataSource: DataSource?,
                                    isFirstResource: Boolean
                            ): Boolean {
                                shimmer.stopShimmer()
                                shimmer.setShimmer(null)
                                return false
                            }

                            override fun onLoadFailed(
                                    e: GlideException?,
                                    model: Any?,
                                    target: Target<Drawable>?,
                                    isFirstResource: Boolean
                            ): Boolean {
                                return false
                            }
                        }
                )
                .into(image)
        // image.setColorFilter(R.color.image_filter, PorterDuff.Mode.LIGHTEN)

        val imageResourceId =
                itemView.resources.getIdentifier(
                        dogModel.image,
                        "drawable",
                        itemView.context.packageName
                )
        image.setBackgroundResource(imageResourceId)
        saved.setImageResource(
                if (dogModel.favorite) R.drawable.ic_icon_bookmark
                else R.drawable.ic_icon_bookmark_unsaved
        )
        name.text = dogModel.name
        breed.text = dogModel.breed
        subBreed.text = dogModel.subBreed
        stats.text = "${dogModel.age.toString()} / ${dogModel.gender}"


        saved.setOnClickListener {

            // Obtener una instancia de la base de datos
            val appDatabase = AppDatabase.getDatabase(itemView.context)

            // Verificar que la base de datos no sea nula
            if (appDatabase != null) {
                // Obtener una instancia de DogDao
                val dogDao = appDatabase.DogDao()

                // Actualizar el campo favorite del perro en la base de datos
                dog.favorite = !dog.favorite
                dogDao.updateDog(dog)

                // Actualizar la imagen del ícono de guardado
                saved.setImageResource(
                    if (dog.favorite) R.drawable.ic_icon_bookmark
                    else R.drawable.ic_icon_bookmark_unsaved
                )
            }
        }

    }
}
