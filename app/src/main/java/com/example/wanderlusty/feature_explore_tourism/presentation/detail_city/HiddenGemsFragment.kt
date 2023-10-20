package com.example.wanderlusty.feature_explore_tourism.presentation.detail_city

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wanderlusty.databinding.FragmentHiddenGemsBinding
import com.example.wanderlusty.feature_explore_tourism.presentation.detail_city.adapter.HiddenRestaurantAdapter
import com.example.wanderlusty.feature_explore_tourism.presentation.detail_city.adapter.HiddenTourismAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHiddenGemsBinding.inflate(inflater, container, false)
        val id = arguments?.getString(OverviewFragment.ARG_CITY_ID) ?: ""
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

        val id = arguments?.getString(OverviewFragment.ARG_CITY_ID) ?: ""
        Log.d(ContentValues.TAG, "onViewCreated:  $id")
        viewModel.getCityDetailHiddenGems(id)

        val state = viewModel.detailCityState.value

        if (state.isLoading) {
            Log.d("HiddenGemsFragment", "Loading")
        } else if (state.error != null) {
            Log.d("HiddenGemsFragment", "Error: ${state.error}")
        } else {
            state.cardTourism?.let {
                hiddenTourismAdapter.setItems(it)
            }
            state.cardRestaurant?.let {
                hiddenRestaurantAdapter.setItems(it)
            }
        }
    }

    private fun initAdapter() {
        binding.rvHiddenRestaurant.apply {
            adapter = hiddenRestaurantAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        binding.rvHiddenTourism.apply {
            adapter = hiddenTourismAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
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