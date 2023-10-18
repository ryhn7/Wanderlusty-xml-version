package com.example.wanderlusty.feature_explore_tourism.presentation.detail_tourism

import com.example.wanderlusty.feature_explore_tourism.domain.entity.TourOption
import com.example.wanderlusty.feature_explore_tourism.domain.entity.TourismEntity

data class DetailTourismState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val tourism: TourismEntity? = null,
    val tourOption : List<TourOption>? = null,
)
