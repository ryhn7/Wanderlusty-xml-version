package com.example.wanderlusty.feature_explore_tourism.data.datasource

import com.example.wanderlusty.feature_explore_tourism.domain.entity.CategoryEntity
import com.example.wanderlusty.feature_explore_tourism.domain.entity.CityEntity
import com.example.wanderlusty.feature_explore_tourism.domain.entity.TourismEntity
import com.example.wanderlusty.feature_explore_tourism.domain.entity.dummyCategory
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
        val jsonString = GetJson.getJsonFromAssets("WanderlustyDetailTourism.json")
        val jsonObject = JSONObject(jsonString)
        val favoritePlaceList = mutableListOf<TourismEntity>()
        val tourismArray = jsonObject.getJSONObject("tourism").getJSONArray("section_two")

        for (i in 0 until tourismArray.length()) {
            val favoritePlaceData = tourismArray.getJSONObject(i)

            val id = favoritePlaceData.getString("id")
            val image = favoritePlaceData.getString("image")
            val title = favoritePlaceData.getString("title")
            val rating = favoritePlaceData.getString("rating").toDouble()
            val review = favoritePlaceData.getString("review").toInt()
            val type = favoritePlaceData.getString("type")
            val location = favoritePlaceData.getString("location")

            val favoritePlaceEntity =
                TourismEntity(id, image, title, rating, review, type, location)
            favoritePlaceList.add(favoritePlaceEntity)
        }
        return favoritePlaceList
    }

    override fun getHiddenGems(): List<TourismEntity> {
        val jsonString = GetJson.getJsonFromAssets("WanderlustyDetailTourism.json")
        val jsonObject = JSONObject(jsonString)
        val hiddenGemsList = mutableListOf<TourismEntity>()

        val tourismArray = jsonObject.getJSONObject("tourism").getJSONArray("section_one")

        for (i in 0 until tourismArray.length()) {
            val hiddenGemsData = tourismArray.getJSONObject(i)

            val id = hiddenGemsData.getString("id")
            val image = hiddenGemsData.getString("image")
            val title = hiddenGemsData.getString("title")
            val rating = hiddenGemsData.getString("rating").toDouble()
            val review = hiddenGemsData.getString("review").toInt()
            val type = hiddenGemsData.getString("type")
            val location = hiddenGemsData.getString("location")

            val hiddenGemsEntity = TourismEntity(id, image, title, rating, review, type, location)
            hiddenGemsList.add(hiddenGemsEntity)
        }
        return hiddenGemsList
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

