package com.example.wanderlusty.feature_explore_tourism.domain.use_case

import androidx.lifecycle.LiveData
import com.example.wanderlusty.feature_explore_tourism.data.model.Category
import com.example.wanderlusty.feature_explore_tourism.domain.interface_repository.TourismRepository
import com.example.wanderlusty.utils.ResultState

class GetAllTourismCategories(private val repository: TourismRepository) {
    suspend operator fun invoke(): LiveData<ResultState<List<Category>>> {
        return repository.getAllTourismCategories()
    }
}