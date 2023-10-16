package com.example.wanderlusty.feature_explore_tourism.data.datasource

import com.example.wanderlusty.feature_explore_tourism.data.model.TourismEntity
import com.example.wanderlusty.feature_explore_tourism.data.model.dummyFavoritePlace
import com.example.wanderlusty.feature_explore_tourism.data.model.dummyHiddenGems
import com.example.wanderlusty.feature_explore_tourism.domain.entity.CategoryEntity
import com.example.wanderlusty.feature_explore_tourism.domain.entity.CityEntity
import com.example.wanderlusty.feature_explore_tourism.domain.entity.dummyCategory
import com.example.wanderlusty.utils.GetJson
import com.example.wanderlusty.utils.JsonParser
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

//        val keys = mapOf(
//            "city_1" to "city_1",
//            "id" to "id",
//            "name" to "name",
//            "image" to "image"
//        )
//
//        val cityJsonMap = JsonParser.parseEntity(jsonString, keys)
//        return cityJsonMap.values.map {
//            val cityData = it.split(",")
//            CityEntity(
//                id = cityData[0],
//                name = cityData[1],
//                image = cityData[2].toInt()
//            )
//        }

        val cityList = mutableListOf<CityEntity>()

        // Iterate through each key (which represents a city)
        for (key in jsonObject.keys()) {
            val cityData = jsonObject.getJSONObject(key)

            val id = cityData.getString("id")
            val name = cityData.getString("name")
            val image = cityData.getString("image")

            // Create a CityEntity and add it to the list
            val cityEntity = CityEntity(id, name, image)
            cityList.add(cityEntity)
        }

        return cityList
    }
}

