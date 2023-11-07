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
import com.yaundeCode.examenAdopcionApp.DogsViewModel
import com.yaundeCode.examenAdopcionApp.R
import com.yaundeCode.examenAdopcionApp.adapter.DogAdapter
import com.yaundecode.examenadopcionapp.database.AppDatabase
import com.yaundecode.examenadopcionapp.database.dogDao
import kotlinx.coroutines.launch

class DogsListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dogAdapter: DogAdapter
    private lateinit var dogsViewModel: DogsViewModel
    private lateinit var v: View
    private var db: AppDatabase? = null
    private var dogDao: dogDao? = null

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
        return v
    }

    override fun onStart() {
        super.onStart()
        db = AppDatabase.getAppDataBase(v.context)
        dogDao = db?.DogDao()
        lifecycleScope.launch {
            dogDao?.getAll()?.let {
                dogAdapter.updateData(it)
            }
            // Solo agrego un separador entre items del recyclerview
            //val dividerItemDecoration = DividerItemDecoration(taskRecyclerView.context, layoutManager.orientation)
            //taskRecyclerView.addItemDecoration(dividerItemDecoration)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dogsViewModel = ViewModelProvider(this)[DogsViewModel::class.java]
        dogsViewModel.dogList.observe(
                viewLifecycleOwner,
                Observer { dogs -> dogAdapter.updateData(dogs) }
        )
        loadDogs()
    }

    private fun loadDogs() {
        dogsViewModel.loadDogs()
    }
}
