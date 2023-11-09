package com.yaundeCode.examenAdopcionApp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.yaundeAode.examenAdopcionApp.database.AppDatabase
import com.yaundeCode.examenAdopcionApp.BreedsViewModel
import com.yaundeCode.examenAdopcionApp.DogsViewModel
import com.yaundeCode.examenAdopcionApp.R
import com.yaundeCode.examenAdopcionApp.adapter.BreedAdapter
import com.yaundeCode.examenAdopcionApp.adapter.DogAdapter
import com.yaundeCode.examenAdopcionApp.database.DogDao
import com.yaundeCode.examenAdopcionApp.models.Breed
import com.yaundeCode.examenAdopcionApp.listener.OnFilterSelectedListener
import com.yaundeCode.examenAdopcionApp.models.Dog
import java.util.Date


class DogsListFragment : Fragment(), OnFilterSelectedListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dogAdapter: DogAdapter
    private lateinit var dogsViewModel: DogsViewModel
    private lateinit var breedReciclerView: RecyclerView
    private lateinit var breedAdapter: BreedAdapter
    private lateinit var breedViewModel: BreedsViewModel
    private lateinit var v: View
    private lateinit var searchBar: View
    private var dogList: List<Dog> = listOf()
    private var breedList: List<Breed> = listOf()
    private var searchQuery: String = ""
    private var isFilteredByModal = false
    private var username: String? = null

    companion object {
        fun newInstance(username: String): DogsListFragment {
            return DogsListFragment().apply {
                arguments = Bundle().apply {
                    putString("username", username!!)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        username = arguments?.getString("username")

        v = inflater.inflate(R.layout.fragment_dogs_list, container, false)
        recyclerView = v.findViewById(R.id.dogsListRecycler)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        dogAdapter = DogAdapter(username!!)
        recyclerView.adapter = dogAdapter

        breedReciclerView = v.findViewById(R.id.breedsListRecycler)
        breedReciclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        breedAdapter = BreedAdapter()
        breedReciclerView.adapter = breedAdapter

        val filterButtonMoreOptions: Button = v.findViewById(R.id.filterButtonMoreOptions)
        filterButtonMoreOptions.setOnClickListener {
            if (isFilteredByModal) {
                updateDogs()
                isFilteredByModal = false
            }

            val dialog = FilterDialogFragment(this)
            dialog.show(parentFragmentManager, "FilterDialogFragment")
        }

        searchBar = v.findViewById(R.id.searchBarFragmentContainer)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dogsViewModel = ViewModelProvider(this)[DogsViewModel::class.java]
        breedViewModel = ViewModelProvider(this)[BreedsViewModel::class.java]

        dogsViewModel.dogList.observe(viewLifecycleOwner) { dogs ->
            dogList = dogs
            filterDogs()
        }

        breedAdapter.selectedBreeds.observe(viewLifecycleOwner) {
            filterDogs()
        }

        val searchBar = childFragmentManager.findFragmentById(R.id.searchBarFragmentContainer)
                as SearchBarFragment

        searchBar.searchQuery.observe(viewLifecycleOwner) { query ->
            searchQuery = query
            filterDogs()
        }

        breedViewModel.breedList.observe(viewLifecycleOwner) { breeds ->
            breedList = breeds
            breedAdapter.updateData(breeds)
            filterDogs()
        }

        dogsViewModel.loadDogs()
        breedViewModel.getBreeds()
    }

    private fun updateDogs(dogs: List<Dog>) {
        dogAdapter.updateData(dogs)
    }

    private fun updateDogs() {
        dogAdapter.updateData(dogList)
    }

    private fun daleCampeon() : List<Dog> {
        val newDog = Dog(
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSAw1_-K-zzqORGhrDwsvkOJJRcNUGDrLX_6ApOpw-lu5jQfmljxJgP5aT8qLwiIY9Qyao&usqp=CAU",
            name = "Vamos Argentina",
            age = 3,
            gender = "Campeon",
            publishedDate = Date().toString(),
            weight = 70.0,
            description = "Vamos Argentina",
            breed = "Campeon",
            subBreed = "Del Mundo",
            location = "Ciudad de buenos Aires",
            owner = "World Cup",
            status = true,
            favorite = true
        )
        return listOf(newDog)
    }


    private fun filterDogs() {
        var dogs: List<Dog>
        if (searchQuery == "campeon") {
            dogs = daleCampeon()
            updateDogs(dogs)
            return
        }
        val breedsSelected: List<Breed> = breedList.filter { breed -> breed.selected }
        if (searchQuery != "") {
            dogs = dogList.filter { dog -> dog.name.contains(searchQuery, ignoreCase = true) }
        } else {
            dogs = dogList
        }
        if (breedsSelected.isNotEmpty()) {
            dogs =
                dogs.filter { dog -> breedsSelected.any { selectedBreed -> selectedBreed.breed == dog.breed } }
        }
        updateDogs(dogs)
    }

    override fun onResume() {
        super.onResume()

        dogsViewModel.dogList.observe(viewLifecycleOwner) { dogs ->
            dogList = dogs
            filterDogs()
        }

        val searchBar = childFragmentManager.findFragmentById(R.id.searchBarFragmentContainer)
                as SearchBarFragment

        searchBar.searchQuery.observe(viewLifecycleOwner) { query ->
            searchQuery = query
            filterDogs()
        }

        breedViewModel.breedList.observe(viewLifecycleOwner) { breeds ->
            breedList = breeds
            breedAdapter.updateData(breeds)
            filterDogs()
        }
        dogsViewModel.loadDogs()
        breedViewModel.getBreeds()
    }


    override fun onFilterGenderSelected(gender: String) {
        isFilteredByModal = true
        val newList = dogList.filter { dog -> dog.gender == gender }
        if (newList.isEmpty())
            Toast.makeText(context, "No tenemos perros con ese genero", Toast.LENGTH_SHORT).show()
        else updateDogs(newList)
    }

    override fun onFilterAgeSelected(age: Int) {
        isFilteredByModal = true
        val newList = dogList.filter { dog -> dog.age == age }
        if (newList.isEmpty())
            Toast.makeText(context, "No tenemos perros con esa edad", Toast.LENGTH_SHORT).show()
        else updateDogs(newList)
    }
}
