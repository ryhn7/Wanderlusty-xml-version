package com.example.wanderlusty.feature_explore_tourism.domain.use_case

import androidx.lifecycle.LiveData
import com.example.wanderlusty.feature_explore_tourism.data.model.Category
import com.example.wanderlusty.feature_explore_tourism.domain.interface_repository.TourismRepository
import com.example.wanderlusty.utils.ResultState
import kotlinx.coroutines.flow.flow

class GetAllTourismCategories(private val repository: TourismRepository) {
    suspend operator fun invoke(): LiveData<ResultState<List<Category>>> {
        return repository.getAllTourismCategories()
    }

//    operator fun invoke(): LiveData<ResultState<List<Category>>> = flow {
//        emit(ResultState.Loading)
//        emit(repository.getAllTourismCategories())
//    } as LiveData<ResultState<List<Category>>>
}