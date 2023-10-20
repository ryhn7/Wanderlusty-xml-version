package com.example.wanderlusty.feature_explore_tourism.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.wanderlusty.feature_explore_tourism.data.datasource.TourismDataSource
import com.example.wanderlusty.feature_explore_tourism.domain.entity.CategoryEntity
import com.example.wanderlusty.feature_explore_tourism.domain.entity.CityDetailHiddenGems
import com.example.wanderlusty.feature_explore_tourism.domain.entity.CityDetailOverview
import com.example.wanderlusty.feature_explore_tourism.domain.entity.CityEntity
import com.example.wanderlusty.feature_explore_tourism.domain.entity.TourismEntity
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

    override suspend fun getAllSectionCitiesOne(): LiveData<ResultState<List<CityEntity>>> =
        liveData {
            try {
                val response = TourismDataSource.getAllSectionCitiesOne()
                emit(ResultState.Success(response))
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override suspend fun getTourismDetail(
        id: String
    ): LiveData<ResultState<TourismEntity>> = liveData {
        try {
            val response = TourismDataSource.getTourismDetail(id)
            response?.let {
                emit(ResultState.Success(it))
            } ?: emit(ResultState.Error("Data not found"))
        } catch (e: Exception) {
            emit(ResultState.Error(e.message.toString()))
        }
    }

    override suspend fun getCityDetailOverview(id: String): LiveData<ResultState<CityDetailOverview>> =
        liveData {
            try {
                val response = TourismDataSource.getCityDetailOverview(id)
                response?.let {
                    emit(ResultState.Success(it))
                } ?: emit(ResultState.Error("Data not found"))
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override suspend fun getCityDetailHiddenGems(id: String): LiveData<ResultState<CityDetailHiddenGems>> =
        liveData {
            try {
                val response = TourismDataSource.getCityDetailHiddenGems(id)
                response?.let {
                    emit(ResultState.Success(it))
                } ?: emit(ResultState.Error("Data not found"))
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