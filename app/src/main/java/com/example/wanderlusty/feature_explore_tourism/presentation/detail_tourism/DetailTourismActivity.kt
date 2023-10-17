package com.example.wanderlusty.feature_explore_tourism.presentation.detail_tourism

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wanderlusty.databinding.ActivityDetailTourismBinding
import com.example.wanderlusty.feature_explore_tourism.domain.entity.TourOption
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailTourismActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityDetailTourismBinding
    private val binding get() = _binding

    private val viewModel: DetailTourismViewModel by viewModels()

    private val tourOptionAdapter by lazy {
        TourOptionAdapter().apply {
            setOnItemClickCallback(object : TourOptionAdapter.OnItemClickCallback {
                override fun onItemClicked(data: TourOption) {
                    val toast =
                        Toast.makeText(this@DetailTourismActivity, data.name, Toast.LENGTH_SHORT)
                    toast.show()
                }
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailTourismBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()

        intent.getStringExtra(EXTRA_TOURISM_ID)?.let {
            viewModel.getDetailTourism(it)
        }


        val state = viewModel.detailState.value

        if (state.isLoading) {
            Log.d("DetailTourismScreen", "Loading")
        } else if (state.error != null) {
            Log.d("DetailTourismScreen", "Error: ${state.error}")
        } else {
            state.tourOption?.let {
                tourOptionAdapter.setItems(it)
                val imageResource = binding.root.context.resources.getIdentifier(
                    state.tourism?.image,
                    "drawable",
                    binding.root.context.packageName
                )
                binding.imgTourism.setImageResource(imageResource)
                binding.tvTitleTourism.text = state.tourism?.title
                binding.tvReviewValue.text = state.tourism?.review.toString()
                binding.tvTypeTourism.text = state.tourism?.type
                binding.tvDescriptionTourism.text = state.tourism?.description
                binding.tvDuration.text = state.tourism?.duration
                binding.tvTitleAddress.text = state.tourism?.address
            }
        }
    }

    private fun initAdapter() {
        binding.rvTourOption.apply {
            layoutManager = LinearLayoutManager(this@DetailTourismActivity)
            adapter = tourOptionAdapter
        }
    }

    companion object {
        const val EXTRA_TOURISM_ID = "TOURISM_ID"
    }
}