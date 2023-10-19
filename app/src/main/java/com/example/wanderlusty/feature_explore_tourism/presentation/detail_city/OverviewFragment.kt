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
import com.example.wanderlusty.feature_explore_tourism.presentation.detail_city.DetailCityActivity.Companion.EXTRA_CITY_ID
import com.example.wanderlusty.feature_explore_tourism.presentation.detail_city.adapter.RecommendationAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OverviewFragment : Fragment() {
    private var _binding: FragmentOverviewBinding? = null

    private val binding get() = _binding!!

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
        val id = arguments?.getString(ARG_CITY_ID) ?: ""
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

        val id = arguments?.getString(ARG_CITY_ID) ?: ""
        Log.d(TAG, "onViewCreated:  $id")
        viewModel.getCityDetailOverview(id)


        val state = viewModel.detailCityState.value

        if (state.isLoading) {
            Log.d("DetailCity", "Loading")
        } else if (state.error != null) {
            Log.d("DetailCity", "Error: ${state.error}")
        } else {
            state.cardTourism?.let {
                recommendationAdapter.setItems(it)
            }
            state.overviewCity?.let {
                val imageResource = binding.root.context.resources.getIdentifier(
                    state.overviewCity.image,
                    "drawable",
                    binding.root.context.packageName
                )
                binding.imgTourism.setImageResource(imageResource)
                binding.tvTitleCity.text = it.name
                binding.tvSubtitleCity.text = it.subtitle
                binding.tvOverviewCity.text = it.description
            }
        }
    }

    private fun initAdapter() {
        binding.rvTourismRecommendations.apply {
            adapter = recommendationAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    companion object {
        const val ARG_CITY_ID = "city_id"

        fun newInstance(cityId: String): OverviewFragment {
            val args = Bundle()
            args.putString(ARG_CITY_ID, cityId)
            val fragment = OverviewFragment()
            fragment.arguments = args
            return fragment
        }
    }
}