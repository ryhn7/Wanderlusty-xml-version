package com.example.wanderlusty.feature_explore_tourism.presentation.detail_city.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.wanderlusty.feature_explore_tourism.presentation.detail_city.HiddenGemsFragment
import com.example.wanderlusty.feature_explore_tourism.presentation.detail_city.HotelsFragment
import com.example.wanderlusty.feature_explore_tourism.presentation.detail_city.OverviewFragment
import com.example.wanderlusty.feature_explore_tourism.presentation.detail_city.RentVehicleFragment
import com.example.wanderlusty.feature_explore_tourism.presentation.detail_city.RestaurantsFragment
import com.example.wanderlusty.feature_explore_tourism.presentation.detail_city.ThingsToDoFragment

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = OverviewFragment()
            1 -> fragment = HiddenGemsFragment()
            2 -> fragment = ThingsToDoFragment()
            3 -> fragment = HotelsFragment()
            4 -> fragment = RestaurantsFragment()
            5 -> fragment = RentVehicleFragment()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return 6
    }
}