package com.example.wanderlusty.feature_explore_tourism.presentation.detail_city

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wanderlusty.databinding.FragmentOverviewBinding
import com.example.wanderlusty.feature_explore_tourism.presentation.detail_city.adapter.RecommendationAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OverviewFragment : Fragment() {
    private var _binding: FragmentOverviewBinding? = null

    private val binding get() = _binding!!
    private var id: String = ""

    private val viewModel: DetailCityViewModel by viewModels()

    private val recommendationAdapter by lazy {
        RecommendationAdapter()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOverviewBinding.inflate(inflater, container, false)

        arguments?.let {
            id = it.getString(DetailCityActivity.EXTRA_CITY_ID) ?: ""
        }

        val state = viewModel.detailCityState.value

        if (state.isLoading) {
            Log.d("DetailCity", "Loading")
        } else if (state.error != null) {
            Log.d("DetailCity", "Error: ${state.error}")
        } else {
            state.cardTourism?.let {
                recommendationAdapter.setItems(it)
                Log.d(TAG, "onCreateView:   ${it}")
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
//        configScreen()
    }

    private fun initAdapter() {
        binding.rvTourismRecommendations.apply {
            adapter = recommendationAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    companion object {
        const val EXTRA_CITY_ID = "CITY_ID"
    }
}