package com.example.wanderlusty.feature_explore_tourism.domain.use_case

import androidx.lifecycle.LiveData
import com.example.wanderlusty.feature_explore_tourism.domain.entity.CityEntity
import com.example.wanderlusty.feature_explore_tourism.domain.interface_repository.TourismRepository
import com.example.wanderlusty.utils.ResultState

class GetAllSectionCitiesOne(private val repository: TourismRepository) {
    suspend operator fun invoke(): LiveData<ResultState<List<CityEntity>>> {
        return repository.getAllSectionCitiesOne()
    }
}