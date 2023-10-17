package com.example.wanderlusty.feature_explore_tourism.data.datasource

import com.example.wanderlusty.feature_explore_tourism.domain.entity.CategoryEntity
import com.example.wanderlusty.feature_explore_tourism.domain.entity.CityEntity
import com.example.wanderlusty.feature_explore_tourism.domain.entity.TourOption
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
        val tourOptionList = mutableListOf<TourOption>()
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
            val description = favoritePlaceData.getString("description")
            val duration = favoritePlaceData.getString("duration")
            val address = favoritePlaceData.getString("address")

            val tourOptionJsonArray = favoritePlaceData.getJSONArray("tour_option")
            for (j in 0 until tourOptionJsonArray.length()) {
                val tourOptionData = tourOptionJsonArray.getJSONObject(j)
                val name = tourOptionData.getString("name")
                val optionRating = tourOptionData.getString("rating").toDouble()
                val optionReview = tourOptionData.getString("review").toInt()
                val price = tourOptionData.getString("price").toDouble()
                val optionImage = tourOptionData.getString("image")

                val tourOptionEntity =
                    TourOption(name, optionRating, optionReview, price, optionImage)
                tourOptionList.add(tourOptionEntity)
            }
            val favoritePlaceEntity =
                TourismEntity(
                    id,
                    image,
                    title,
                    rating,
                    review,
                    type,
                    location,
                    null,
                    description,
                    duration,
                    address,
                    tourOptionList
                )
            favoritePlaceList.add(favoritePlaceEntity)
        }
        return favoritePlaceList
    }

    override fun getHiddenGems(): List<TourismEntity> {
        val jsonString = GetJson.getJsonFromAssets("WanderlustyDetailTourism.json")
        val jsonObject = JSONObject(jsonString)
        val hiddenGemsList = mutableListOf<TourismEntity>()
        val tourOptionList = mutableListOf<TourOption>()

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
            val description = hiddenGemsData.getString("description")
            val duration = hiddenGemsData.getString("duration")
            val address = hiddenGemsData.getString("address")

            val tourOptionJsonArray = hiddenGemsData.getJSONArray("tour_option")
            for (j in 0 until tourOptionJsonArray.length()) {
                val tourOptionData = tourOptionJsonArray.getJSONObject(j)
                val name = tourOptionData.getString("name")
                val optionRating = tourOptionData.getString("rating").toDouble()
                val optionReview = tourOptionData.getString("review").toInt()
                val price = tourOptionData.getString("price").toDouble()
                val optionImage = tourOptionData.getString("image")

                val tourOptionEntity =
                    TourOption(name, optionRating, optionReview, price, optionImage)
                tourOptionList.add(tourOptionEntity)
            }

            val hiddenGemsEntity = TourismEntity(
                id,
                image,
                title,
                rating,
                review,
                type,
                location,
                null,
                description,
                duration,
                address,
                tourOptionList
            )
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

