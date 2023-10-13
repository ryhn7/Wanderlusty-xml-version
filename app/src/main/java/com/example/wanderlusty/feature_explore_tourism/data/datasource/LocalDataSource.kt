package com.example.wanderlusty.feature_explore_tourism.data.datasource

import com.example.wanderlusty.feature_explore_tourism.data.model.Category
import com.example.wanderlusty.feature_explore_tourism.data.model.Tourism
import com.example.wanderlusty.feature_explore_tourism.data.model.dummyCategory
import com.example.wanderlusty.feature_explore_tourism.data.model.dummyFavoritePlace
import com.example.wanderlusty.feature_explore_tourism.data.model.dummyHiddenGems

interface LocalDataSource {
    fun getAllFavoritePlaces(): List<Tourism>?
    fun getHiddenGems(): List<Tourism>?
    fun getAllTourismCategories(): List<Category>?
}

object TourismDataSource: LocalDataSource {
    override fun getAllFavoritePlaces(): List<Tourism> {
        return dummyFavoritePlace
    }
    override fun getHiddenGems(): List<Tourism> {
        return dummyHiddenGems
    }

    override fun getAllTourismCategories(): List<Category> {
        return dummyCategory
    }
}
