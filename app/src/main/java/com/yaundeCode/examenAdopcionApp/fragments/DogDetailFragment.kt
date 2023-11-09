package com.yaundeCode.examenAdopcionApp.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.gson.Gson
import com.yaundeCode.examenAdopcionApp.DogsViewModel
import com.yaundeCode.examenAdopcionApp.R
import com.yaundeCode.examenAdopcionApp.models.Dog
import kotlin.math.roundToInt


private const val ARG_DOG = "dog"

class DogDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var dog: Dog
    private var dogsViewModel: DogsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dog = Gson().fromJson(it.getString(ARG_DOG), Dog::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dog_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dogsViewModel = ViewModelProvider(this)[DogsViewModel::class.java]
        super.onViewCreated(view, savedInstanceState)

        val action = DogDetailFragmentDirections.actionDogDetailFragmentToDogsListFragment()
        val bottomSheet = view.findViewById<View>(R.id.bottomSheet)
        val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

        view.viewTreeObserver.addOnDrawListener {
            bottomSheetBehavior.peekHeight = view.height - view.findViewById<ImageView>(R.id.dogDetailImage).height
        }

        val dogDetailImage = view.findViewById<ImageView>(R.id.dogDetailImage)
        val bottomSheetName = view.findViewById<TextView>(R.id.bottomSheetName)
        val bottomSheetProvince = view.findViewById<TextView>(R.id.bottomSheetProvince)
        val bottomSheetAge = view.findViewById<TextView>(R.id.bottomSheetAge)
        val bottomSheetSex = view.findViewById<TextView>(R.id.bottomSheetSex)
        val bottomSheetWeight = view.findViewById<TextView>(R.id.bottomSheetWeight)
        val bottomSheetDogDescription = view.findViewById<TextView>(R.id.bottomSheetDogDescription)
        val bottomSheetAdoptButton = view.findViewById<Button>(R.id.bottomSheetAdoptButton)
        val bottomSheetOwner = view.findViewById<TextView>(R.id.bottomSheetOwner)
        val bottomSheetCallButton = view.findViewById<ImageButton>(R.id.bottomSheetCallButton)

        Glide.with(view)
            .load(dog.image)
            .into(dogDetailImage)

        bottomSheetName.text = dog.name
        bottomSheetProvince.text = dog.location
        bottomSheetAge.text = buildString {
            append(dog.age.toString())
            append(getString(
                if (dog.age > 1) R.string.dog_details_age_suffix_plural
                else R.string.dog_details_age_suffix_singular
            ))
        }
        bottomSheetSex.text = dog.gender
        bottomSheetWeight.text = buildString {
            append(dog.weight.roundToInt().toString())
            append(getString(R.string.dog_details_weight_suffix))
        }
//        bottomSheetOwner.text = dog.owner
        bottomSheetDogDescription.text = dog.description
        bottomSheetOwner.text = dog.owner

        bottomSheetAdoptButton.setOnClickListener {
            dog.owner = "Martin"
            dog.status = true
            dogsViewModel?.updateDog(dog)

            findNavController().navigate(action)
        }



        bottomSheetCallButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:1147896500")
            startActivity(intent)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(dogJson: String) =
            DogDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_DOG, dogJson)
                }
            }
    }
}