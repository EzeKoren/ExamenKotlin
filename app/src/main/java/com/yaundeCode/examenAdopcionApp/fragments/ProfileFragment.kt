package com.yaundeCode.examenAdopcionApp.fragments

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.yaundeCode.examenAdopcionApp.MainActivity
import com.yaundeCode.examenAdopcionApp.R

class ProfileFragment : Fragment() {

    companion object {
        private const val MY_PERMISSIONS_REQUEST_READ_STORAGE = 100
        private const val PICK_IMAGE_REQUEST = 101

        @JvmStatic
        fun newInstance(name: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString("name", name)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = arguments?.getString("name")
        val usernameTextView: TextView = view.findViewById(R.id.nav_usernameBox)
        usernameTextView.text = name

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                MY_PERMISSIONS_REQUEST_READ_STORAGE
            )
        }

        // Maneja el clic del botón para abrir el selector de imágenes
        val button: Button = view.findViewById(R.id.button)
        button.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            val imageUri = data?.data
            // Muestra la imagen seleccionada en el ImageView del fragmento
            val imageView: ImageView? = view?.findViewById(R.id.imageProfile)
            imageView?.setImageURI(imageUri)
            // Actualiza la imagen en el Drawer Layout de la actividad principal
            (activity as MainActivity).updateDrawerImage(imageUri!!)
        }
    }
}
