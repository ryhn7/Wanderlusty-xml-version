package com.example.wanderlusty.feature_explore_tourism.presentation.explore

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wanderlusty.R
import com.example.wanderlusty.databinding.FragmentExploreBinding
import com.example.wanderlusty.feature_explore_tourism.domain.entity.CategoryEntity
import com.example.wanderlusty.feature_explore_tourism.domain.entity.CityEntity
import com.example.wanderlusty.feature_explore_tourism.domain.entity.TourismEntity
import com.example.wanderlusty.feature_explore_tourism.presentation.explore.adapter.CategoryAdapter
import com.example.wanderlusty.feature_explore_tourism.presentation.explore.adapter.SectionCityOneAdapter
import com.example.wanderlusty.feature_explore_tourism.presentation.explore.adapter.SectionOneAdapter
import com.example.wanderlusty.feature_explore_tourism.presentation.explore.adapter.SectionTwoAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ExploreFragment : Fragment() {
    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ExploreViewModel by viewModels()

    private val categoryAdapter by lazy {
        CategoryAdapter().apply {
            setOnItemClickCallback(object : CategoryAdapter.OnItemClickCallback {
                override fun onItemClicked(data: CategoryEntity) {
                    val toast =
                        Toast.makeText(requireContext(), data.textCategory, Toast.LENGTH_SHORT)
                    toast.show()
                }
            })
        }
    }

    private val sectionOneAdapter by lazy {
        SectionOneAdapter().apply {
            setOnItemClickCallback(object : SectionOneAdapter.OnItemClickCallback {
                override fun onItemClicked(data: TourismEntity) {
                    val toast =
                        Toast.makeText(requireContext(), data.title, Toast.LENGTH_SHORT)
                    toast.show()
                }
            })
        }
    }

    private val sectionTwoAdapter by lazy {
        SectionTwoAdapter().apply {
            setOnItemClickCallback(object : SectionTwoAdapter.OnItemClickCallback {
                override fun onItemClicked(data: TourismEntity) {
                    val toast =
                        Toast.makeText(requireContext(), data.title, Toast.LENGTH_SHORT)
                    toast.show()
                }
            })
        }
    }

    private val sectionCityOneAdapter by lazy {
        SectionCityOneAdapter().apply {
            setOnItemClickCallback(object : SectionCityOneAdapter.OnItemClickCallback {
                override fun onItemClicked(data: CityEntity) {
                    val toast =
                        Toast.makeText(requireContext(), data.name, Toast.LENGTH_SHORT)
                    toast.show()
                }
            })
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentExploreBinding.inflate(inflater, container, false)

        val state = viewModel.exploreState.value

        if (state.isLoading) {
            Log.d("ExploreScreen", "Loading")
        } else if (state.error != null) {
            Log.d("ExploreScreen", "Error: ${state.error}")
        } else {
            state.tourismCategories?.let { categoryAdapter.setItems(it) }
            state.hiddenGems?.let { sectionOneAdapter.setItems(it) }
            state.favoritePlace?.let { sectionTwoAdapter.setItems(it) }
            state.sectionCitiesOne?.let { sectionCityOneAdapter.setItems(it) }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        configScreen()
    }

    private fun configScreen() {
        //        Section Tourism/City Config
        val titleSectionOne = binding.sectionOne.tvTitleSection
        val subtitleSectionOne = binding.sectionOne.tvSubtitleSection
        titleSectionOne.text = getString(R.string.section_hidden_gems)
        subtitleSectionOne.text = getString(R.string.subtitle_hidden_gems)

        val titleSectionTwo = binding.sectionTwo.tvTitleSection
        val subtitleSectionTwo = binding.sectionTwo.tvSubtitleSection
        titleSectionTwo.text = getString(R.string.section_favorite_place)
        subtitleSectionTwo.text = getString(R.string.subtitle_favorite_place)
//        End Section Tourism/City Config

//        Section Promotion Config
        val titlePromotionOne = binding.sectionPromotion.tvTitlePromotion
        val subtitlePromotionOne = binding.sectionPromotion.tvSubtitlePromotion
        val imgPromotionOne = binding.sectionPromotion.ivPromotion
        val btnPromotionOne = binding.sectionPromotion.btnPromotion
        titlePromotionOne.text = getString(R.string.title_promotion)
        subtitlePromotionOne.text = getString(R.string.subtitle_promotion)
        imgPromotionOne.setImageResource(R.drawable.coastal)
        btnPromotionOne.text = getString(R.string.button_promotion)
        btnPromotionOne.setOnClickListener {
            val toast =
                Toast.makeText(requireContext(), "Redirect to promotion", Toast.LENGTH_SHORT)
            toast.show()
        }
//        End Section Promotion Config


//        Section Add Tourism Config
        val btnAddTourism = binding.btnAddTourism
        btnAddTourism.setOnClickListener {
            val intent = Intent(requireContext(), AddTourismActivity::class.java)
            startActivity(intent)
        }
//        End Section Add Tourism Config
    }

    private fun initAdapter() {
        binding.rvCategories.apply {
            adapter = categoryAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        binding.rvSectionOne.apply {
            adapter = sectionOneAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        binding.rvSectionTwo.apply {
            adapter = sectionTwoAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        binding.rvSectionCityOne.apply {
            adapter = sectionCityOneAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }
}