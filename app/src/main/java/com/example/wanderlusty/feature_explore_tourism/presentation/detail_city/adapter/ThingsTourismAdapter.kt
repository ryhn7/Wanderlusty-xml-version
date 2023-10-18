package com.example.wanderlusty.feature_explore_tourism.presentation.detail_city.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.wanderlusty.databinding.ItemCardOneBinding
import com.example.wanderlusty.feature_explore_tourism.domain.entity.TourismSpot
import com.example.wanderlusty.feature_explore_tourism.presentation.detail_tourism.DetailTourismActivity
import com.example.wanderlusty.utils.BaseAdapter
import com.example.wanderlusty.utils.DiffCallbackListener

class ThingsTourismAdapter : BaseAdapter<TourismSpot, ItemCardOneBinding>(diffCallbackListener) {

    companion object {
        val diffCallbackListener = object : DiffCallbackListener<TourismSpot> {
            override fun areItemsTheSame(oldItem: TourismSpot, newItem: TourismSpot) =
                oldItem.id == newItem.id
        }
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun createViewHolder(inflater: LayoutInflater, container: ViewGroup) =
        ItemCardOneBinding.inflate(inflater, container, false)

    override fun bind(binding: ItemCardOneBinding, item: TourismSpot, position: Int, count: Int) {
        val firstImage = item.image[0]
        val imageResource = binding.root.context.resources.getIdentifier(
            firstImage,
            "drawable",
            binding.root.context.packageName
        )
        binding.imgCardOne.setImageResource(imageResource)
        binding.tvTitleCardOne.text = item.name
        binding.tvRating.text = item.rating.toString()
        binding.tvReview.text = String.format("(%s)", item.review)
        binding.typeCardOne.text = item.type
        binding.locationCardOne.text = item.location
        binding.root.setOnClickListener {
            binding.root.context.startActivity(
                Intent(
                    binding.root.context,
                    DetailTourismActivity::class.java
                ).putExtra(DetailTourismActivity.EXTRA_TOURISM_ID, item.id)
            )
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: TourismSpot)
    }
}