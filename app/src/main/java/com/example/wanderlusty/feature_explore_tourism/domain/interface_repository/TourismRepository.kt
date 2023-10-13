package com.example.wanderlusty.feature_explore_tourism.domain.interface_repository

import androidx.lifecycle.LiveData
import com.example.wanderlusty.feature_explore_tourism.data.model.Category
import com.example.wanderlusty.feature_explore_tourism.data.model.Tourism
import com.example.wanderlusty.utils.ResultState

interface TourismRepository {

    suspend fun getAllFavoritePlace() : LiveData<ResultState<List<Tourism>>>
    suspend fun getHiddenGems() : LiveData<ResultState<List<Tourism>>>
    suspend fun getAllTourismCategories() : LiveData<ResultState<List<Category>>>
}