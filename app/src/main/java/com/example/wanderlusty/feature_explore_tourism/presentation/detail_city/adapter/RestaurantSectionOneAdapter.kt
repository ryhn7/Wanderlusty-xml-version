package com.example.wanderlusty.feature_explore_tourism.presentation.detail_city.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.wanderlusty.databinding.ItemCardOneBinding
import com.example.wanderlusty.feature_explore_tourism.domain.entity.Restaurant
import com.example.wanderlusty.utils.BaseAdapter
import com.example.wanderlusty.utils.DiffCallbackListener

class RestaurantSectionOneAdapter :
    BaseAdapter<Restaurant, ItemCardOneBinding>(diffCallbackListener) {

    companion object {
        val diffCallbackListener = object : DiffCallbackListener<Restaurant> {
            override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant) =
                oldItem.name == newItem.name
        }
    }

    private lateinit var onItemClickCallback: RestaurantSectionOneAdapter.OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: RestaurantSectionOneAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun createViewHolder(inflater: LayoutInflater, container: ViewGroup) =
        ItemCardOneBinding.inflate(inflater, container, false)

    override fun bind(binding: ItemCardOneBinding, item: Restaurant, position: Int, count: Int) {
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
        binding.locationCardOne.text = item.price1.toString()
        binding.root.setOnClickListener {
            onItemClickCallback.onItemClicked(item)
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Restaurant)
    }
}