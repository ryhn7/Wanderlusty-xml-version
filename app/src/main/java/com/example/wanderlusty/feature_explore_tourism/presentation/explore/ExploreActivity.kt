package com.example.wanderlusty.feature_explore_tourism.presentation.explore

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wanderlusty.R
import com.example.wanderlusty.databinding.ActivityExploreBinding
import com.example.wanderlusty.feature_explore_tourism.data.model.TourismEntity
import com.example.wanderlusty.feature_explore_tourism.domain.entity.CategoryEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreActivity : AppCompatActivity() {

    private var _binding: ActivityExploreBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ExploreViewModel by viewModels()

    private val categoryAdapter by lazy {
        CategoryAdapter().apply {
            setOnItemClickCallback(object : CategoryAdapter.OnItemClickCallback {
                override fun onItemClicked(data: CategoryEntity) {
                    val toast =
                        Toast.makeText(applicationContext, data.textCategory, Toast.LENGTH_SHORT)
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
                        Toast.makeText(applicationContext, data.title, Toast.LENGTH_SHORT)
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
                        Toast.makeText(applicationContext, data.title, Toast.LENGTH_SHORT)
                    toast.show()
                }
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityExploreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()

        val titleSectionOne = binding.sectionOne.tvTitleSection
        val subtitleSectionOne = binding.sectionOne.tvSubtitleSection
        titleSectionOne.text = getString(R.string.section_hidden_gems)
        subtitleSectionOne.text = getString(R.string.subtitle_hidden_gems)

        val titleSectionTwo = binding.sectionTwo.tvTitleSection
        val subtitleSectionTwo = binding.sectionTwo.tvSubtitleSection
        titleSectionTwo.text = getString(R.string.section_favorite_place)
        subtitleSectionTwo.text = getString(R.string.subtitle_favorite_place)

        val state = viewModel.exploreState.value

        if (state.isLoading) {
            Log.d("ExploreScreen", "Loading")
        } else if (state.error != null) {
            Log.d("ExploreScreen", "Error: ${state.error}")
        } else {
            state.tourismCategories?.let { categoryAdapter.setItems(it) }
            state.hiddenGems?.let { sectionOneAdapter.setItems(it) }
            state.favoritePlace?.let { sectionTwoAdapter.setItems(it) }
        }
    }

    private fun initAdapter() {
        binding.rvCategories.apply {
            adapter = categoryAdapter
            layoutManager =
                LinearLayoutManager(this@ExploreActivity, LinearLayoutManager.HORIZONTAL, false)
        }

        binding.rvSectionOne.apply {
            adapter = sectionOneAdapter
            layoutManager =
                LinearLayoutManager(this@ExploreActivity, LinearLayoutManager.HORIZONTAL, false)
        }

        binding.rvSectionTwo.apply {
            adapter = sectionTwoAdapter
            layoutManager =
                LinearLayoutManager(this@ExploreActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }
}