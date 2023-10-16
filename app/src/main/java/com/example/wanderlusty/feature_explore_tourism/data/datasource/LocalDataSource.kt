package com.example.wanderlusty.feature_explore_tourism.data.datasource

import com.example.wanderlusty.feature_explore_tourism.data.model.TourismEntity
import com.example.wanderlusty.feature_explore_tourism.data.model.dummyFavoritePlace
import com.example.wanderlusty.feature_explore_tourism.data.model.dummyHiddenGems
import com.example.wanderlusty.feature_explore_tourism.domain.entity.CategoryEntity
import com.example.wanderlusty.feature_explore_tourism.domain.entity.CityEntity
import com.example.wanderlusty.feature_explore_tourism.domain.entity.dummyCategory

interface LocalDataSource {
    fun getAllFavoritePlaces(): List<TourismEntity>?
    fun getHiddenGems(): List<TourismEntity>?
    fun getAllTourismCategories(): List<CategoryEntity>?
    fun getAllSectionCitiesOne(): List<CityEntity>?
}

object TourismDataSource : LocalDataSource {
    override fun getAllFavoritePlaces(): List<TourismEntity> {
        return dummyFavoritePlace
    }

    override fun getHiddenGems(): List<TourismEntity> {
        return dummyHiddenGems
    }

    override fun getAllTourismCategories(): List<CategoryEntity> {
        return dummyCategory
    }

    override fun getAllSectionCitiesOne(): List<CityEntity>? {
        TODO("Not yet implemented")
    }
}
