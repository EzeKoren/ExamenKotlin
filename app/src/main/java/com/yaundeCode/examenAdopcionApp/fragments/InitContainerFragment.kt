package com.yaundecode.examenadopcionapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.yaundecode.examenadopcionapp.R
import com.yaundecode.examenadopcionapp.SliderAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass. Use the [InitContainerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InitContainerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val v = inflater.inflate(R.layout.fragment_init_container, container, false)
        val button = v.findViewById<Button>(R.id.get_started_button)
        button.setOnClickListener {
            findNavController().navigate(R.id.action_initContainerFragment3_to_loginFragment)
        }
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)
        val sliderAdapter = SliderAdapter(requireActivity())
        viewPager.adapter = sliderAdapter
    }

    companion object {
        /**
         * Use this factory method to create a new instance of this fragment using the provided
         * parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InitContainerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                InitContainerFragment().apply {
                    arguments =
                            Bundle().apply {
                                putString(ARG_PARAM1, param1)
                                putString(ARG_PARAM2, param2)
                            }
                }
    }
}
