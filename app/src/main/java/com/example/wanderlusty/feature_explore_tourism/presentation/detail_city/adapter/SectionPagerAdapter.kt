package com.example.wanderlusty.feature_explore_tourism.presentation.detail_city.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.wanderlusty.feature_explore_tourism.presentation.detail_city.HiddenGemsFragment
import com.example.wanderlusty.feature_explore_tourism.presentation.detail_city.HotelsFragment
import com.example.wanderlusty.feature_explore_tourism.presentation.detail_city.OverviewFragment
import com.example.wanderlusty.feature_explore_tourism.presentation.detail_city.RentVehicleFragment
import com.example.wanderlusty.feature_explore_tourism.presentation.detail_city.RestaurantsFragment
import com.example.wanderlusty.feature_explore_tourism.presentation.detail_city.ThingsToDoFragment

class SectionsPagerAdapter(activity: FragmentActivity, private val cityId: String) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 6

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OverviewFragment.newInstance(cityId) // Pass the cityId to the OverviewFragment
            1 -> HiddenGemsFragment()
            2 -> ThingsToDoFragment()
            3 -> HotelsFragment()
            4 -> RestaurantsFragment()
            5 -> RentVehicleFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}