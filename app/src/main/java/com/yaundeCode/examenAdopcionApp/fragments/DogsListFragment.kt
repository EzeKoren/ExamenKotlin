package com.yaundecode.examenadopcionapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yaundecode.examenadopcionapp.DogsViewModel
import com.yaundecode.examenadopcionapp.R
import com.yaundecode.examenadopcionapp.adapter.DogAdapter



class DogsListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dogAdapter: DogAdapter
    private lateinit var dogsViewModel: DogsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dogs_list, container, false)
        recyclerView = view.findViewById(R.id.dogsListRecycler)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        dogAdapter = DogAdapter()
        recyclerView.adapter = dogAdapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dogsViewModel = ViewModelProvider(this)[DogsViewModel::class.java]
        dogsViewModel.dogList.observe(viewLifecycleOwner, Observer { dogs ->
            dogAdapter.updateData(dogs)
        })
        loadDogs()
    }

    private fun loadDogs() {
        dogsViewModel.loadDogs()
    }
}
