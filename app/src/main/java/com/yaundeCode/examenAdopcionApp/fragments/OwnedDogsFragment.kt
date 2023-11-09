package com.yaundeCode.examenAdopcionApp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yaundeAode.examenAdopcionApp.database.AppDatabase
import com.yaundeCode.examenAdopcionApp.DogsViewModel
import com.yaundeCode.examenAdopcionApp.R
import com.yaundeCode.examenAdopcionApp.adapter.DogAdapter
import com.yaundeCode.examenAdopcionApp.database.DogDao
import com.yaundeCode.examenAdopcionApp.models.Dog

class OwnedDogsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dogAdapter: DogAdapter
    private lateinit var dogsViewModel: DogsViewModel
    private var newDogList = mutableListOf<Dog>()
    private lateinit var v: View
    private var db: AppDatabase? = null
    private var dogDao: DogDao? = null

    // TODO: Cambiar por lo de Javi
    private val HARDCODED_NAME: String = "Martin"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_owned_dogs, container, false)
        recyclerView = v.findViewById(R.id.ownedDogsListRecycler)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        dogAdapter = DogAdapter()
        recyclerView.adapter = dogAdapter
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dogsViewModel = ViewModelProvider(this)[DogsViewModel::class.java]
        dogsViewModel.loadDogs()

        dogsViewModel.dogList.observe(viewLifecycleOwner) { dogs ->
            dogAdapter.updateData(dogs.filter { dog -> dog.owner == HARDCODED_NAME })
        }
    }

    override fun onResume() {
        super.onResume()

        dogsViewModel = ViewModelProvider(this)[DogsViewModel::class.java]
        dogsViewModel.loadDogs()

        dogsViewModel.dogList.observe(viewLifecycleOwner) { dogs ->
            dogAdapter.updateData(dogs.filter { dog -> dog.owner == HARDCODED_NAME })
        }
    }
}
