package com.yaundeCode.examenAdopcionApp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yaundeAode.examenAdopcionApp.database.AppDatabase
import com.yaundeCode.examenAdopcionApp.BreedsViewModel
import com.yaundeCode.examenAdopcionApp.DogsViewModel
import com.yaundeCode.examenAdopcionApp.R
import com.yaundeCode.examenAdopcionApp.adapter.BreedAdapter
import com.yaundeCode.examenAdopcionApp.adapter.DogAdapter
import com.yaundeCode.examenAdopcionApp.database.DogDao

class DogsListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dogAdapter: DogAdapter
    private lateinit var dogsViewModel: DogsViewModel
    private lateinit var breedReciclerView: RecyclerView
    private lateinit var breedAdapter: BreedAdapter
    private lateinit var breedViewModel: BreedsViewModel
    private lateinit var v: View
    private var db: AppDatabase? = null
    private var dogDao: DogDao? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_dogs_list, container, false)
        recyclerView = v.findViewById(R.id.dogsListRecycler)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        dogAdapter = DogAdapter()
        recyclerView.adapter = dogAdapter
        breedReciclerView = v.findViewById(R.id.breedsListRecycler)
        breedReciclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        breedAdapter = BreedAdapter()
        breedReciclerView.adapter = breedAdapter
        return v
    }

    override fun onStart() {
        super.onStart()
        db = AppDatabase.getAppDataBase(v.context)
        dogDao = db?.DogDao()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dogsViewModel = ViewModelProvider(this)[DogsViewModel::class.java]
        dogsViewModel.dogList.observe(
                viewLifecycleOwner,
                Observer { dogs -> dogAdapter.updateData(dogs) }
        )
        loadDogs()
        breedViewModel = ViewModelProvider(this)[BreedsViewModel::class.java]
        breedViewModel.breedList.observe(viewLifecycleOwner,
            Observer { breeds -> breedAdapter.updateData(breeds) }
            )
        loadBreeds()
    }

    private fun loadDogs() {
        dogsViewModel.loadDogs()
    }

    private fun loadBreeds() {
        breedViewModel.getBreeds()
    }
}
