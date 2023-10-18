package com.example.wanderlusty.feature_explore_tourism.presentation.detail_tourism

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wanderlusty.R
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
        val halfStar = binding.icHalfStar

        val starTotal = 5;

        val starPercentage = (rating.toFloat() / starTotal.toFloat()) * 100
        val starPercentageRounded = starPercentage.toInt()
        Log.d(TAG, "setRatingStars:   $starPercentageRounded")

        star1.visibility = View.INVISIBLE
        halfStar.visibility = View.INVISIBLE

        val parentLayout = binding.llContainerStarReview
        parentLayout.removeAllViews()

        when (starPercentageRounded) {
            in 0 until 10 -> {
                // Show 0 star
            }

            in 10 until 20 -> {
                addStars(parentLayout, 0, 1)
            }

            in 20 until 30 -> {
                addStars(parentLayout, 1, 0)
            }

            in 30 until 40 -> {
                addStars(parentLayout, 1, 1)
            }

            in 40 until 50 -> {
                addStars(parentLayout, 2, 0)
            }

            in 50 until 60 -> {
                addStars(parentLayout, 2, 1)
            }

            in 60 until 70 -> {
                addStars(parentLayout, 3, 0)
            }

            in 70 until 80 -> {
                addStars(parentLayout, 3, 1)
            }

            in 80 until 90 -> {
                addStars(parentLayout, 4, 0)
            }

            in 90 until 100 -> {
                addStars(parentLayout, 4, 1)
            }

            100 -> {
                addStars(parentLayout, 5, 0)
            }
        }
    }


    private fun addStars(parentLayout: LinearLayout, fullStars: Int, halfStar: Int) {
        for (i in 0 until fullStars) {
            val newStar = ImageView(this)
            val params = LinearLayout.LayoutParams(
                resources.getDimensionPixelSize(R.dimen.star_size),
                resources.getDimensionPixelSize(R.dimen.star_size)
            )
            newStar.layoutParams = params
            newStar.setImageResource(R.drawable.ic_star_fill)
            newStar.contentDescription = getString(R.string.icon)
            parentLayout.addView(newStar)
        }

        if (halfStar == 1) {
            val newHalfStar = ImageView(this)
            val params = LinearLayout.LayoutParams(
                resources.getDimensionPixelSize(R.dimen.star_size), // Set the size here
                resources.getDimensionPixelSize(R.dimen.star_size)
            )
            newHalfStar.layoutParams = params
            newHalfStar.setImageResource(R.drawable.ic_half_star_fill)
            newHalfStar.contentDescription = getString(R.string.icon)
            parentLayout.addView(newHalfStar)
        }
    }


    companion object {
        const val EXTRA_TOURISM_ID = "TOURISM_ID"
    }
}