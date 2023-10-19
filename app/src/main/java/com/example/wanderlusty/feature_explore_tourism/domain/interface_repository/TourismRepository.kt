package com.example.wanderlusty.feature_explore_tourism.domain.interface_repository

import androidx.lifecycle.LiveData
import com.example.wanderlusty.feature_explore_tourism.domain.entity.CategoryEntity
import com.example.wanderlusty.feature_explore_tourism.domain.entity.CityDetailEntity
import com.example.wanderlusty.feature_explore_tourism.domain.entity.CityDetailOverview
import com.example.wanderlusty.feature_explore_tourism.domain.entity.CityEntity
import com.example.wanderlusty.feature_explore_tourism.domain.entity.TourismEntity
import com.example.wanderlusty.utils.ResultState

interface TourismRepository {

    suspend fun getAllFavoritePlace(): LiveData<ResultState<List<TourismEntity>>>
    suspend fun getHiddenGems(): LiveData<ResultState<List<TourismEntity>>>
    suspend fun getAllTourismCategories(): LiveData<ResultState<List<CategoryEntity>>>
    suspend fun getAllSectionCitiesOne(): LiveData<ResultState<List<CityEntity>>>
    suspend fun getTourismDetail(id: String): LiveData<ResultState<TourismEntity>>
    suspend fun getCityDetailOverview(id: String): LiveData<ResultState<CityDetailOverview>>
}