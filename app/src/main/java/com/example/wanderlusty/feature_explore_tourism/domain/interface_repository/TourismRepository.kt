package com.example.wanderlusty.feature_explore_tourism.domain.interface_repository

import androidx.lifecycle.LiveData
import com.example.wanderlusty.feature_explore_tourism.data.model.TourismEntity
import com.example.wanderlusty.feature_explore_tourism.domain.entity.CategoryEntity
import com.example.wanderlusty.utils.ResultState

interface TourismRepository {

    suspend fun getAllFavoritePlace(): LiveData<ResultState<List<TourismEntity>>>
    suspend fun getHiddenGems(): LiveData<ResultState<List<TourismEntity>>>
    suspend fun getAllTourismCategories(): LiveData<ResultState<List<CategoryEntity>>>
}