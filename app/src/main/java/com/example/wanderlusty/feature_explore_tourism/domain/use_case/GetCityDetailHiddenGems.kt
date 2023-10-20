package com.example.wanderlusty.feature_explore_tourism.domain.use_case

import com.example.wanderlusty.feature_explore_tourism.domain.interface_repository.TourismRepository

data class GetCityDetailHiddenGems(
    private val repository: TourismRepository
) {
    suspend operator fun invoke(id: String) =
        repository.getCityDetailHiddenGems(id)
}