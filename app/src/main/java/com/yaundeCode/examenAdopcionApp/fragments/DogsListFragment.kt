

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yaundeCode.examenAdopcionApp.DogsViewModel
import com.yaundeCode.examenAdopcionApp.R
import com.yaundeCode.examenAdopcionApp.database.AppDatabase
import com.yaundeCode.examenAdopcionApp.database.DogDao

class DogsListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dogAdapter: DogAdapter
    private lateinit var dogsViewModel: DogsViewModel
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

        val dogSaveIcon = v.findViewById<ImageView>(R.id.dogSaveIcon)
        dogSaveIcon.setOnClickListener {
            Toast.makeText(requireContext(), "Mensaje de ejemplo", Toast.LENGTH_SHORT).show()
        }

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
    }

    inner class DogAdapter : RecyclerView.Adapter<DogAdapter.DogViewHolder>() {
        // ...

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.dog_item, parent, false)
            val dogSaveIcon = view.findViewById<ImageView>(R.id.dogSaveIcon)
            dogSaveIcon.setOnClickListener {
                Toast.makeText(requireContext(), "Mensaje de ejemplo", Toast.LENGTH_SHORT).show()
            }
            return DogViewHolder(view)
        }

        override fun onBindViewHolder(holder: DogAdapter.DogViewHolder, position: Int) {
            TODO("Not yet implemented")
        }

        override fun getItemCount(): Int {
            TODO("Not yet implemented")
        }

        // ...
    }

    private fun loadDogs() {
        dogsViewModel.loadDogs()
    }
}
