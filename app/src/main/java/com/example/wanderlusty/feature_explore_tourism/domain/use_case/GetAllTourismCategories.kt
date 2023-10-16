package com.example.wanderlusty.feature_explore_tourism.domain.use_case

import androidx.lifecycle.LiveData
import com.example.wanderlusty.feature_explore_tourism.domain.interface_repository.TourismRepository
import com.example.wanderlusty.feature_explore_tourism.domain.entity.CategoryEntity
import com.example.wanderlusty.utils.ResultState

class GetAllTourismCategories(private val repository: TourismRepository) {
    suspend operator fun invoke(): LiveData<ResultState<List<CategoryEntity>>> {
        return repository.getAllTourismCategories()
    }
}