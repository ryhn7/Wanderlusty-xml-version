package com.example.wanderlusty.feature_explore_tourism.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.wanderlusty.R

data class Category(
    val id: String,
    @DrawableRes val imageCategory: Int,
    @StringRes val textCategory: Int
)

val dummyCategory = listOf(
    Category("1", R.drawable.ic_forest, R.string.forest),
    Category("2", R.drawable.ic_beach, R.string.beach),
    Category("3", R.drawable.ic_mountain, R.string.mountain),
    Category("4", R.drawable.ic_canyon, R.string.canyon),
    Category("5", R.drawable.ic_river, R.string.river),
    Category("6", R.drawable.ic_cave, R.string.cave),
)