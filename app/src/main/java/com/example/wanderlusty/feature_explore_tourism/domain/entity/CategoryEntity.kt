package com.example.wanderlusty.feature_explore_tourism.domain.entity

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.wanderlusty.R

data class CategoryEntity(
    val id: String,
    @DrawableRes val imageCategory: Int,
    @StringRes val textCategory: Int
)

val dummyCategory = listOf(
    CategoryEntity("1", R.drawable.ic_forest, R.string.forest),
    CategoryEntity("2", R.drawable.ic_beach, R.string.beach),
    CategoryEntity("3", R.drawable.ic_mountain, R.string.mountain),
    CategoryEntity("4", R.drawable.ic_canyon, R.string.canyon),
    CategoryEntity("5", R.drawable.ic_river, R.string.river),
    CategoryEntity("6", R.drawable.ic_cave, R.string.cave),
)