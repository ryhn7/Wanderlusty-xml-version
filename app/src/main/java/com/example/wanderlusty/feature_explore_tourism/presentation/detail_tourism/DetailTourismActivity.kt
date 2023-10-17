package com.example.wanderlusty.feature_explore_tourism.presentation.detail_tourism

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.View
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

        val iconBack = binding.icBack
        iconBack.setOnClickListener {
            onBackPressed()
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
                val reviewValue = state.tourism?.review.toString()
                val underlinedText = SpannableString(reviewValue)
                underlinedText.setSpan(UnderlineSpan(), 0, underlinedText.length, 0)
                binding.tvReviewValue.text = underlinedText
                binding.tvTypeTourism.text = state.tourism?.type
                binding.tvDescriptionTourism.text = state.tourism?.description
                binding.tvDuration.text = state.tourism?.duration
                val addressValue = state.tourism?.address
                val underlinedAddress = SpannableString(addressValue)
                underlinedAddress.setSpan(UnderlineSpan(), 0, underlinedAddress.length, 0)
                binding.tvAddressValue.text = underlinedAddress
                val rating = state.tourism?.rating
                if (rating != null) {
                    setRatingStars(rating)
                }
            }
        }
    }

    private fun initAdapter() {
        binding.rvTourOption.apply {
            layoutManager = LinearLayoutManager(this@DetailTourismActivity)
            adapter = tourOptionAdapter
        }
    }

    private fun setRatingStars(rating: Number) {
        val star1 = binding.icStar1
        val star2 = binding.icStar2
        val star3 = binding.icStar3
        val star4 = binding.icStar4
        val star5 = binding.icStar5
        val halfStar = binding.icHalfStar

        // Round the rating to the nearest integer
        val roundedRating = rating.toInt()

        // Set the stars based on the rounded rating
        when (roundedRating) {
            1 -> {
                star1.visibility = View.VISIBLE
                halfStar.visibility = View.INVISIBLE
            }

            2 -> {
                star1.visibility = View.VISIBLE
                star2.visibility = View.VISIBLE
                halfStar.visibility = View.INVISIBLE
            }

            3 -> {
                star1.visibility = View.VISIBLE
                star2.visibility = View.VISIBLE
                star3.visibility = View.VISIBLE
                halfStar.visibility = View.INVISIBLE
            }

            4 -> {
                star1.visibility = View.VISIBLE
                star2.visibility = View.VISIBLE
                star3.visibility = View.VISIBLE
                star4.visibility = View.VISIBLE
                halfStar.visibility = View.INVISIBLE
            }

            5 -> {
                star1.visibility = View.VISIBLE
                star2.visibility = View.VISIBLE
                star3.visibility = View.VISIBLE
                star4.visibility = View.VISIBLE
                halfStar.visibility = View.VISIBLE
            }
        }
    }


    companion object {
        const val EXTRA_TOURISM_ID = "TOURISM_ID"
    }
}