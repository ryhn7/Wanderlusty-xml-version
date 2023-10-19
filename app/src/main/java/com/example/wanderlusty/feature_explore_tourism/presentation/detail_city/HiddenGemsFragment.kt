package com.example.wanderlusty.feature_explore_tourism.presentation.detail_city

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.wanderlusty.R
import com.example.wanderlusty.databinding.FragmentHiddenGemsBinding
import com.example.wanderlusty.feature_explore_tourism.presentation.detail_city.adapter.HiddenRestaurantAdapter
import com.example.wanderlusty.feature_explore_tourism.presentation.detail_city.adapter.HiddenTourismAdapter

class HiddenGemsFragment : Fragment() {
    private var _binding: FragmentHiddenGemsBinding? = null

    private val binding get() = _binding!!

    private val viewModel: DetailCityViewModel by viewModels()

    private val hiddenTourismAdapter by lazy {
        HiddenTourismAdapter()
    }

    private val hiddenRestaurantAdapter by lazy {
        HiddenRestaurantAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHiddenGemsBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        const val ARG_CITY_ID = "city_id"

        fun newInstance(cityId: String): HiddenGemsFragment {
            val args = Bundle()
            args.putString(ARG_CITY_ID, cityId)
            val fragment = HiddenGemsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}