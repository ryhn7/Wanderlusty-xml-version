package com.example.wanderlusty.feature_explore_tourism.data.datasource

import com.example.wanderlusty.feature_explore_tourism.domain.entity.CategoryEntity
import com.example.wanderlusty.feature_explore_tourism.domain.entity.CityDetailEntity
import com.example.wanderlusty.feature_explore_tourism.domain.entity.CityDetailHiddenGems
import com.example.wanderlusty.feature_explore_tourism.domain.entity.CityDetailOverview
import com.example.wanderlusty.feature_explore_tourism.domain.entity.CityEntity
import com.example.wanderlusty.feature_explore_tourism.domain.entity.HiddenGems
import com.example.wanderlusty.feature_explore_tourism.domain.entity.Restaurant
import com.example.wanderlusty.feature_explore_tourism.domain.entity.TourOption
import com.example.wanderlusty.feature_explore_tourism.domain.entity.TourismEntity
import com.example.wanderlusty.feature_explore_tourism.domain.entity.TourismSpot
import com.example.wanderlusty.feature_explore_tourism.domain.entity.dummyCategory
import com.example.wanderlusty.utils.GetJson
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

interface LocalDataSource {
    fun getAllFavoritePlaces(): List<TourismEntity>?
    fun getHiddenGems(): List<TourismEntity>?
    fun getAllTourismCategories(): List<CategoryEntity>?
    fun getAllSectionCitiesOne(): List<CityEntity>?
    fun getTourismDetail(id: String): TourismEntity?
    fun getCityDetailOverview(id: String): CityDetailOverview?
    fun getCityDetailHiddenGems(id: String): CityDetailHiddenGems?
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

    override fun getTourismDetail(id: String): TourismEntity? {
        val jsonString = GetJson.getJsonFromAssets("WanderlustyDetailTourism.json")
        val jsonObject = JSONObject(jsonString)


        val tourismArray = jsonObject.getJSONObject("tourism")

        for (sectionKey in tourismArray.keys()) {
            val section = tourismArray.getJSONArray(sectionKey)
            for (i in 0 until section.length()) {
                val item = section.getJSONObject(i)

                if (item.getString("id") == id) {
                    val tourOptionList = item.getJSONArray("tour_option")
                        .let { options ->
                            (0 until options.length()).map { j ->
                                options.getJSONObject(j).run {
                                    TourOption(
                                        getString("name"),
                                        getString("rating").toDouble(),
                                        getString("review").toInt(),
                                        getString("price").toDouble(),
                                        getString("image")
                                    )
                                }
                            }
                        }
                    return TourismEntity(
                        item.getString("id"),
                        item.getString("image"),
                        item.getString("title"),
                        item.getString("rating").toDouble(),
                        item.getString("review").toInt(),
                        item.getString("type"),
                        item.getString("location"),
                        item.optString("price"),
                        item.getString("description"),
                        item.getString("duration"),
                        item.getString("address"),
                        tourOptionList
                    )
                }
            }
        }
        return null
    }

    override fun getCityDetailOverview(id: String): CityDetailOverview? {
        val jsonString = GetJson.getJsonFromAssets("WanderlustyDetail.json")
        val jsonObject = JSONObject(jsonString)
        val cityData = jsonObject.optJSONObject("city_$id") ?: return null

        // Extract city details
        val cityId = cityData.optString("id", "")
        val cityName = cityData.optString("name", "")
        val citySubtitle = cityData.optString("subtitle", "")
        val cityImage = cityData.optString("image", "")
        val cityDescription = cityData.optString("description", "")

        // Extract recommendation list
        val recommendationList = cityData.optJSONArray("recommendation")?.let { recommendations ->
            (0 until recommendations.length()).map { i ->
                recommendations.getJSONObject(i).run {

                    val imagesString = getString("image")
                    val imageList = Regex("\"(\\w+)\"").findAll(imagesString).map { it.groupValues[1] }.toList()


                    TourismSpot(
                        getString("id"),
                        getString("name"),
                        getString("description"),
                        imageList,
                        getString("rating").toDouble(),
                        getString("review").toDouble(),
                        getString("type"),
                        getString("location"),
                        getTourOptionList(optJSONArray("tour_option"))
                    )
                }
            }
        } ?: emptyList()

        return CityDetailOverview(
            cityId,
            cityName,
            cityImage,
            citySubtitle,
            cityDescription,
            recommendationList
        )
    }

    override fun getCityDetailHiddenGems(id: String): CityDetailHiddenGems? {
        val jsonString = GetJson.getJsonFromAssets("WanderlustyDetail.json")
        val jsonObject = JSONObject(jsonString)
        val cityData = jsonObject.optJSONObject("city_$id") ?: return null

        // Extract city details
        val cityId = cityData.optString("id", "")
        val hiddenGemsData = cityData.optJSONObject("hidden_gems") ?: return null

        val hiddenTourismArray = hiddenGemsData.optJSONArray("hidden_tourism")
        val hiddenTourismList = getHiddenTourismList(hiddenTourismArray)

        val hiddenRestaurantArray = hiddenGemsData.optJSONArray("hidden_restaurant")
        val hiddenRestaurantList = getHiddenRestaurantList(hiddenRestaurantArray)

        return CityDetailHiddenGems(cityId, HiddenGems(hiddenTourismList, hiddenRestaurantList))
    }

    private fun getHiddenTourismList(tourismArray: JSONArray?): List<TourismSpot> {
        return tourismArray?.let {
            (0 until it.length()).map { i ->
                it.getJSONObject(i).run {

                    val imagesString = getString("image")
                    val imageList = Regex("\"(\\w+)\"").findAll(imagesString).map { it.groupValues[1] }.toList()

                    TourismSpot(
                        getString("id"),
                        getString("name"),
                        getString("description"),
                        imageList,
                        getString("rating").toDouble(),
                        getString("review").toDouble(),
                        getString("type"),
                        getString("location"),
                        getTourOptionList(optJSONArray("tour_option"))
                    )
                }
            }
        } ?: emptyList()
    }

    private fun getHiddenRestaurantList(restaurantArray: JSONArray?): List<Restaurant> {
        return restaurantArray?.let {
            (0 until it.length()).map { i ->
                it.getJSONObject(i).run {
                    Restaurant(
                        getString("name"),
                        getString("description"),
                        getStringArray("image"),
                        getDouble("rating"),
                        getDouble("review"),
                        getInt("price1"),
                        getInt("price2"),
                        getBoolean("sponsored"),
                        getString("type"),
                        getString("duration"),
                        getString("address"),
                        getTourOptionList(optJSONArray("tour_option"))
                    )
                }
            }
        } ?: emptyList()
    }

    private fun JSONObject.getStringArray(key: String): List<String> {
        val jsonArray = getJSONArray(key)
        return (0 until jsonArray.length()).map { jsonArray.getString(it) }
    }

    private fun getTourOptionList(tourOptionArray: JSONArray?): List<TourOption> {
        return tourOptionArray?.let {
            (0 until it.length()).map { i ->
                it.getJSONObject(i).run {
                    TourOption(
                        getString("name"),
                        getNumber("rating"),
                        getNumber("review"),
                        getNumber("price"),
                        getString("image")
                    )
                }
            }
        } ?: emptyList()
    }

    private fun JSONObject.getNumber(key: String): Number {
        return try {
            this.getDouble(key)
        } catch (e: JSONException) {
            this.getInt(key)
        }
    }
}

