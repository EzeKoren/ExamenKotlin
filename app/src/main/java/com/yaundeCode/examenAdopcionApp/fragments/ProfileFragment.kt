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
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.yaundeCode.examenAdopcionApp.MainActivity
import com.yaundeCode.examenAdopcionApp.R

class ProfileFragment : Fragment() {

    companion object {
        private const val MY_PERMISSIONS_REQUEST_READ_STORAGE = 100
        private const val PICK_IMAGE_REQUEST = 101
        private lateinit var resultLauncher: ActivityResultLauncher<Intent>


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
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.title = ""
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val imageUri = result.data?.data
                val imageView: ImageView? = view?.findViewById(R.id.imageProfile)
                imageView?.setImageURI(imageUri)
                (activity as MainActivity).updateDrawerImage(imageUri!!)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = arguments?.getString("name")
        val usernameTextView: TextView = view.findViewById(R.id.nav_usernameBox)
        usernameTextView.text = name

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                MY_PERMISSIONS_REQUEST_READ_STORAGE
            )
        }

        val button: Button = view.findViewById(R.id.button)
        button.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            resultLauncher.launch(intent)
        }
    }

}
