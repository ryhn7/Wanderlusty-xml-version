package com.example.wanderlusty.feature_explore_tourism.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.wanderlusty.feature_explore_tourism.data.datasource.TourismDataSource
import com.example.wanderlusty.feature_explore_tourism.data.model.TourismEntity
import com.example.wanderlusty.feature_explore_tourism.domain.entity.CategoryEntity
import com.example.wanderlusty.feature_explore_tourism.domain.interface_repository.TourismRepository
import com.example.wanderlusty.utils.ResultState

class TourismRepositoryImpl private constructor() : TourismRepository {
    override suspend fun getAllFavoritePlace(): LiveData<ResultState<List<TourismEntity>>> =
        liveData {
            try {
                val response = TourismDataSource.getAllFavoritePlaces()
                emit(ResultState.Success(response))
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override suspend fun getHiddenGems(): LiveData<ResultState<List<TourismEntity>>> = liveData {
        try {
            val response = TourismDataSource.getHiddenGems()
            emit(ResultState.Success(response))
        } catch (e: Exception) {
            emit(ResultState.Error(e.message.toString()))
        }
    }

    override suspend fun getAllTourismCategories(): LiveData<ResultState<List<CategoryEntity>>> =
        liveData {
            try {
                val response = TourismDataSource.getAllTourismCategories()
                emit(ResultState.Success(response))
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }

    companion object {
        @Volatile
        private var instance: TourismRepositoryImpl? = null

        fun getInstance(): TourismRepositoryImpl =
            instance ?: synchronized(this) {
                instance ?: TourismRepositoryImpl()
            }
    }
}