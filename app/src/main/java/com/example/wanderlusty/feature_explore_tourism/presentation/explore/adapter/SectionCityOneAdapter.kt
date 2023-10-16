package com.example.wanderlusty.feature_explore_tourism.presentation.explore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.wanderlusty.databinding.ItemCardTwoBinding
import com.example.wanderlusty.feature_explore_tourism.domain.entity.CityEntity
import com.example.wanderlusty.utils.BaseAdapter
import com.example.wanderlusty.utils.DiffCallbackListener

class SectionCityOneAdapter : BaseAdapter<CityEntity, ItemCardTwoBinding>(diffCallbackListener) {

    companion object {
        val diffCallbackListener = object : DiffCallbackListener<CityEntity> {
            override fun areItemsTheSame(oldItem: CityEntity, newItem: CityEntity) =
                oldItem.id == newItem.id
        }
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun createViewHolder(inflater: LayoutInflater, container: ViewGroup) =
        ItemCardTwoBinding.inflate(inflater, container, false)

    override fun bind(
        binding: ItemCardTwoBinding,
        item: CityEntity,
        position: Int,
        count: Int
    ) {
        val imageResource = binding.root.context.resources.getIdentifier(
            item.image,
            "drawable",
            binding.root.context.packageName
        )
        binding.imgCardTwo.setImageResource(imageResource)
        binding.tvTitleCardTwo.text = item.name
        binding.root.setOnClickListener {
            onItemClickCallback.onItemClicked(item)
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: CityEntity)
    }
}


