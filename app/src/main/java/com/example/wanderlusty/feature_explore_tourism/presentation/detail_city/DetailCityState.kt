package com.example.wanderlusty.feature_explore_tourism.presentation.detail_city

import com.example.wanderlusty.feature_explore_tourism.domain.entity.CityDetailEntity
import com.example.wanderlusty.feature_explore_tourism.domain.entity.TourOption

data class DetailCityState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val city: CityDetailEntity? = null,
)