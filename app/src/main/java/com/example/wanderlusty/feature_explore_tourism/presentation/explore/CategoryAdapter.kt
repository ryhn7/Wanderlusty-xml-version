package com.example.wanderlusty.feature_explore_tourism.presentation.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.wanderlusty.databinding.ItemCategoryCardBinding
import com.example.wanderlusty.feature_explore_tourism.data.model.Category
import com.example.wanderlusty.utils.BaseAdapter
import com.example.wanderlusty.utils.DiffCallbackListener

class CategoryAdapter : BaseAdapter<Category, ItemCategoryCardBinding>(diffCallbackListener) {

    companion object {
        val diffCallbackListener = object : DiffCallbackListener<Category> {
            override fun areItemsTheSame(oldItem: Category, newItem: Category) =
                oldItem.id == newItem.id
        }
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun createViewHolder(inflater: LayoutInflater, container: ViewGroup) =
        ItemCategoryCardBinding.inflate(inflater, container, false)

    override fun bind(binding: ItemCategoryCardBinding, item: Category, position: Int, count: Int) {
        binding.imgCategory.setImageResource(item.imageCategory)
        binding.tvCategoryName.setText(item.textCategory)
        binding.root.setOnClickListener {
            onItemClickCallback.onItemClicked(item)
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Category)
    }
}