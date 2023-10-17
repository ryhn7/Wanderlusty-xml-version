package com.example.wanderlusty.feature_explore_tourism.presentation.explore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.wanderlusty.databinding.ItemCardOneBinding
import com.example.wanderlusty.feature_explore_tourism.domain.entity.TourismEntity
import com.example.wanderlusty.utils.BaseAdapter
import com.example.wanderlusty.utils.DiffCallbackListener

class SectionOneAdapter : BaseAdapter<TourismEntity, ItemCardOneBinding>(diffCallbackListener) {

    companion object {
        val diffCallbackListener = object : DiffCallbackListener<TourismEntity> {
            override fun areItemsTheSame(oldItem: TourismEntity, newItem: TourismEntity) =
                oldItem.id == newItem.id
        }
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun createViewHolder(inflater: LayoutInflater, container: ViewGroup) =
        ItemCardOneBinding.inflate(inflater, container, false)

    override fun bind(
        binding: ItemCardOneBinding,
        item: TourismEntity,
        position: Int,
        count: Int
    ) {
        binding.imgCardOne.setImageResource(item.image)
        binding.tvTitleCardOne.text = item.title
        binding.tvRating.text = item.rating.toString()
        binding.tvReview.text = item.review.toString()
        binding.typeCardOne.text = item.type
        binding.locationCardOne.text = item.location
        binding.root.setOnClickListener {
            onItemClickCallback.onItemClicked(item)
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: TourismEntity)
    }
}