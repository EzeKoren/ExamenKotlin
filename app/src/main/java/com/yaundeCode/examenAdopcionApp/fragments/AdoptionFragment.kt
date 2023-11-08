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
import com.yaundeCode.examenAdopcionApp.DogsViewModel
import com.yaundeCode.examenAdopcionApp.R
import com.yaundeCode.examenAdopcionApp.adapter.DogAdapter

class AdoptionFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dogAdapter: DogAdapter
    private lateinit var dogsViewModel: DogsViewModel
    private lateinit var v: View

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_adoption, container, false)
        recyclerView = v.findViewById(R.id.dogsAdoptListRecycler)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        dogAdapter = DogAdapter()
        recyclerView.adapter = dogAdapter
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val owner = "Martin"
        dogsViewModel = ViewModelProvider(this)[DogsViewModel::class.java]
        dogsViewModel.loadDogsAdopt(owner)

        dogsViewModel.dogList.observe(
            viewLifecycleOwner,
            Observer { dogs -> dogAdapter.updateData(dogs) }
        )
    }

    override fun onResume() {
        super.onResume()
        val owner = "Martin"
        dogsViewModel = ViewModelProvider(this)[DogsViewModel::class.java]
        dogsViewModel.loadDogsAdopt(owner)

        dogsViewModel.dogList.observe(
            viewLifecycleOwner,
            Observer { dogs -> dogAdapter.updateData(dogs) }
        )
    }

}
