package com.example.wanderlusty.feature_explore_tourism.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.wanderlusty.R

data class Category(
    @DrawableRes val imageCategory: Int,
    @StringRes val textCategory: Int
)

val dummyCategory = listOf(
    Category(R.drawable.ic_forest, R.string.forest),
    Category(R.drawable.ic_beach, R.string.beach),
    Category(R.drawable.ic_mountain, R.string.mountain),
    Category(R.drawable.ic_canyon, R.string.canyon),
    Category(R.drawable.ic_river, R.string.river),
    Category(R.drawable.ic_cave, R.string.cave),
)