package com.example.wanderlusty.feature_explore_tourism.domain.use_case

import com.example.wanderlusty.feature_explore_tourism.domain.interface_repository.TourismRepository

data class GetCityDetailOverview(
    private val repository: TourismRepository
) {
    suspend operator fun invoke(id: String) =
        repository.getCityDetailOverview(id)
}
