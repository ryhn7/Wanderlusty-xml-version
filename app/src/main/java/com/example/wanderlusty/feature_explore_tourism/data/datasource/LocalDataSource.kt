package com.example.wanderlusty.feature_explore_tourism.data.datasource

import com.example.wanderlusty.feature_explore_tourism.domain.entity.CategoryEntity
import com.example.wanderlusty.feature_explore_tourism.domain.entity.CityEntity
import com.example.wanderlusty.feature_explore_tourism.domain.entity.TourismEntity
import com.example.wanderlusty.feature_explore_tourism.domain.entity.dummyCategory
import com.example.wanderlusty.feature_explore_tourism.domain.entity.dummyFavoritePlace
import com.example.wanderlusty.feature_explore_tourism.domain.entity.dummyHiddenGems
import com.example.wanderlusty.utils.GetJson
import org.json.JSONObject

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

    override fun getAllSectionCitiesOne(): List<CityEntity> {
        val jsonString = GetJson.getJsonFromAssets("WanderlustyDetail.json")
        val jsonObject = JSONObject(jsonString)
        val cityList = mutableListOf<CityEntity>()

        for (key in jsonObject.keys()) {
            val cityData = jsonObject.getJSONObject(key)

            val id = cityData.getString("id")
            val name = cityData.getString("name")
            val image = cityData.getString("image")

            val cityEntity = CityEntity(id, name, image)
            cityList.add(cityEntity)
        }
        return cityList
    }
}

