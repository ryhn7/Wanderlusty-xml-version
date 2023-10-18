package com.example.wanderlusty.feature_explore_tourism.presentation.detail_tourism

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.wanderlusty.databinding.ItemCardFiveBinding
import com.example.wanderlusty.feature_explore_tourism.domain.entity.TourOption
import com.example.wanderlusty.utils.BaseAdapter
import com.example.wanderlusty.utils.DiffCallbackListener

class TourOptionAdapter : BaseAdapter<TourOption, ItemCardFiveBinding>(diffCallbackListener) {

    companion object {
        val diffCallbackListener = object : DiffCallbackListener<TourOption> {
            override fun areItemsTheSame(oldItem: TourOption, newItem: TourOption): Boolean {
                TODO("Not yet implemented")
            }

        }
    }

    private lateinit var onItemClickCallback: TourOptionAdapter.OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: TourOptionAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun createViewHolder(inflater: LayoutInflater, container: ViewGroup) =
        ItemCardFiveBinding.inflate(inflater, container, false)

    override fun bind(
        binding: ItemCardFiveBinding,
        item: TourOption, position: Int,
        count: Int
    ) {
        val imageResource = binding.root.context.resources.getIdentifier(
            item.image,
            "drawable",
            binding.root.context.packageName
        )
        binding.imgCardFive.setImageResource(imageResource)
        binding.tvTitleCardFive.text = item.name
        binding.tvRating.text = item.rating.toString()
        binding.tvReview.text = String.format("(%s)", item.review)
        binding.tvPrice.text = String.format("from Rp. %s per person", item.price)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: TourOption)
    }
}