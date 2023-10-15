package com.example.wanderlusty.feature_explore_tourism.presentation.explore

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wanderlusty.databinding.ActivityExploreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreActivity : AppCompatActivity() {

    private var _binding: ActivityExploreBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ExploreViewModel by viewModels()

    private val categoryAdapter by lazy {
        CategoryAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityExploreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()

        val state = viewModel.exploreState.value

        if (state.isLoading) {
            Log.d("ExploreScreen", "Loading")
        } else if (state.error != null) {
            Log.d("ExploreScreen", "Error: ${state.error}")
        } else {
            state.tourismCategories?.let { categoryAdapter.setItems(it) }
        }
    }

    private fun initAdapter() {
        binding.rvCategories.apply {
            adapter = categoryAdapter
            layoutManager =
                LinearLayoutManager(this@ExploreActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }
}