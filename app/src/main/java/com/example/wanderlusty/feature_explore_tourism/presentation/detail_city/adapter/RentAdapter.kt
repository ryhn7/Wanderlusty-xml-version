package com.example.wanderlusty.feature_explore_tourism.presentation.detail_city.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.wanderlusty.databinding.ItemCardThreeBinding
import com.example.wanderlusty.feature_explore_tourism.domain.entity.Rental
import com.example.wanderlusty.utils.BaseAdapter
import com.example.wanderlusty.utils.DiffCallbackListener

class RentAdapter : BaseAdapter<Rental, ItemCardThreeBinding>(diffCallbackListener) {

    companion object {
        val diffCallbackListener = object : DiffCallbackListener<Rental> {
            override fun areItemsTheSame(oldItem: Rental, newItem: Rental) =
                oldItem.name == newItem.name
        }
    }

    private lateinit var onItemClickCallback: RentAdapter.OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: RentAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun createViewHolder(inflater: LayoutInflater, container: ViewGroup) =
        ItemCardThreeBinding.inflate(inflater, container, false)

    override fun bind(binding: ItemCardThreeBinding, item: Rental, position: Int, count: Int) {
        val firstImage = item.image[0]
        val imageResource = binding.root.context.resources.getIdentifier(
            firstImage,
            "drawable",
            binding.root.context.packageName
        )
        binding.imgCardThree.setImageResource(imageResource)
        binding.tvTitleCardThree.text = item.name
        binding.tvRating.text = item.rating.toString()
        binding.tvReview.text = String.format("(%s)", item.review)
        binding.root.setOnClickListener {
            onItemClickCallback.onItemClicked(item)
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Rental)
    }
}