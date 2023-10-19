package com.example.wanderlusty.feature_explore_tourism.presentation.detail_city

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.wanderlusty.R
import com.example.wanderlusty.databinding.ActivityDetailCityBinding
import com.example.wanderlusty.feature_explore_tourism.presentation.detail_city.adapter.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailCityActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityDetailCityBinding

    private val binding get() = _binding

    private val viewModel: DetailCityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailCityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setViewPager()


        Log.d(TAG, "onCreate:   ${intent.getStringExtra(EXTRA_CITY_ID)}")

//        val iconBack = binding.icBack
//        iconBack.setOnClickListener {
//            onBackPressed()
//        }

    }

    private fun setViewPager() {
        val sectionsPagerAdapter =
            intent.getStringExtra(EXTRA_CITY_ID)?.let { SectionsPagerAdapter(this, it) }
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabLayout


        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLE[position])
        }.attach()
    }

    companion object {
        const val EXTRA_CITY_ID = "CITY_ID"
        private val TAB_TITLE = intArrayOf(
            R.string.overview,
            R.string.hidden_gems,
            R.string.things,
            R.string.hotels,
            R.string.restaurants,
            R.string.rent_vehicle
        )
    }
}