package com.yaundecode.examenadopcionapp.adapter

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.facebook.shimmer.ShimmerFrameLayout
import com.yaundecode.examenadopcionapp.models.Dog
import com.yaundecode.examenadopcionapp.R


class DogViewHolder(dogView: View) : RecyclerView.ViewHolder(dogView) {

    private val image = dogView.findViewById<ImageView>(R.id.dogImageView)
    private val shimmer = dogView.findViewById<ShimmerFrameLayout>(R.id.dogImageShimmer)
    private val saved = dogView.findViewById<ImageView>(R.id.dogSaveIcon)
    private val name = dogView.findViewById<TextView>(R.id.textDogName)
    private val breed = dogView.findViewById<TextView>(R.id.textDogBreed)
    private val subBreed = dogView.findViewById<TextView>(R.id.textDogSubBreed)
    private val stats = dogView.findViewById<TextView>(R.id.textDogStats)

    fun render(dogModel: Dog) {
        shimmer.startShimmer()

        Glide.with(itemView)
            .load(dogModel.image)
            .listener(object : RequestListener<Drawable> {
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
            })
            .into(image)
        // image.setColorFilter(R.color.image_filter, PorterDuff.Mode.LIGHTEN)

        val imageResourceId = itemView.resources.getIdentifier(dogModel.image, "drawable", itemView.context.packageName)
        image.setBackgroundResource(imageResourceId)
        saved.setImageResource(if (dogModel.favorite) R.drawable.ic_icon_bookmark else R.drawable.ic_icon_bookmark_unsaved)
        name.text = dogModel.name
        breed.text = dogModel.breed
        subBreed.text = dogModel.subBreed
        stats.text = "${dogModel.age.toString()} / ${dogModel.gender}"
    }
}
