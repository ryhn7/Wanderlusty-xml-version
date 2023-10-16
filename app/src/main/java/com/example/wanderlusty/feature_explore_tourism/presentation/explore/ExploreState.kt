package com.example.wanderlusty.feature_explore_tourism.presentation.explore

import com.example.wanderlusty.feature_explore_tourism.domain.entity.CategoryEntity
import com.example.wanderlusty.feature_explore_tourism.domain.entity.CityEntity
import com.example.wanderlusty.feature_explore_tourism.domain.entity.TourismEntity

data class ExploreState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val hiddenGems: List<TourismEntity>? = null,
    val favoritePlace: List<TourismEntity>? = null,
    val tourismCategories: List<CategoryEntity>? = null,
    val sectionCitiesOne: List<CityEntity>? = null,
)
