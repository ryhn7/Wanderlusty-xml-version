package com.example.wanderlusty.feature_explore_tourism.presentation.explore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.wanderlusty.databinding.ItemCategoryCardBinding
import com.example.wanderlusty.feature_explore_tourism.domain.entity.CategoryEntity
import com.example.wanderlusty.utils.BaseAdapter
import com.example.wanderlusty.utils.DiffCallbackListener

class CategoryAdapter : BaseAdapter<CategoryEntity, ItemCategoryCardBinding>(diffCallbackListener) {

    companion object {
        val diffCallbackListener = object : DiffCallbackListener<CategoryEntity> {
            override fun areItemsTheSame(oldItem: CategoryEntity, newItem: CategoryEntity) =
                oldItem.id == newItem.id
        }
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun createViewHolder(inflater: LayoutInflater, container: ViewGroup) =
        ItemCategoryCardBinding.inflate(inflater, container, false)

    override fun bind(
        binding: ItemCategoryCardBinding,
        item: CategoryEntity,
        position: Int,
        count: Int
    ) {
        binding.imgCategory.setImageResource(item.imageCategory)
        binding.tvCategoryName.setText(item.textCategory)
        binding.root.setOnClickListener {
            onItemClickCallback.onItemClicked(item)
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: CategoryEntity)
    }
}