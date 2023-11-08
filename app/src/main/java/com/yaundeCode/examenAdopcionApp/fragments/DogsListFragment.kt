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

class DogsListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dogAdapter: DogAdapter
    private lateinit var dogsViewModel: DogsViewModel
    private lateinit var v: View
    private lateinit var searchBar: View
    private var dogList: List<Dog> = listOf()
    private var searchQuery: String = ""
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
        searchBar = v.findViewById(R.id.searchBarFragmentContainer)

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
        dogsViewModel.dogList.observe(viewLifecycleOwner) { dogs ->
            dogList = dogs
            if (searchQuery != "") filterDogs() else updateDogs()
        }
        loadDogs()

        val searchBar = childFragmentManager.findFragmentById(R.id.searchBarFragmentContainer)
                    as SearchBarFragment

        searchBar.searchQuery.observe(viewLifecycleOwner) { query ->
            searchQuery = query
            filterDogs()
        }
    }

    private fun updateDogs(dogs: List<Dog>) {
        dogAdapter.updateData(dogs)
    }

    private fun updateDogs() {
        dogAdapter.updateData(dogList)
    }


    private fun filterDogs() {
        if (searchQuery == "") updateDogs(dogList)
        else {
            var dogs: List<Dog> = dogList.filter { dog -> dog.name.contains(searchQuery, ignoreCase = true) }
            updateDogs(dogs)
        }
    }

    private fun loadDogs() {
        dogsViewModel.loadDogs()
    }
}
