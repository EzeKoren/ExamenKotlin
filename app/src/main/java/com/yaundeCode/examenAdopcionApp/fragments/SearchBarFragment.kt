package com.yaundeCode.examenAdopcionApp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.lifecycle.MutableLiveData
import com.google.android.material.textfield.TextInputEditText
import com.yaundeCode.examenAdopcionApp.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchBarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class SearchBarFragment : Fragment() {
    val searchQuery = MutableLiveData<String>("")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_bar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchButton: ImageButton = view.findViewById(R.id.seeker_button)
        val searchText: TextInputEditText = view.findViewById(R.id.seeker_text_input)

        searchButton.setOnClickListener {
            val query = searchText.text.toString()

            if (query == searchQuery.value) {
                searchQuery.value = ""
                searchText.setText("")
            } else searchQuery.value = query
        }
    }
}