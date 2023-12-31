package com.yaundeCode.examenAdopcionApp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yaundeCode.examenAdopcionApp.fragments.SliderIntroOneFragment
import com.yaundeCode.examenAdopcionApp.fragments.SliderIntroThreeFragment
import com.yaundeCode.examenAdopcionApp.fragments.SliderIntroTwoFragment

class SliderAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 3 // Número total de pantallas
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SliderIntroOneFragment()
            1 -> SliderIntroTwoFragment()
            else -> SliderIntroThreeFragment()
        }
    }
}
