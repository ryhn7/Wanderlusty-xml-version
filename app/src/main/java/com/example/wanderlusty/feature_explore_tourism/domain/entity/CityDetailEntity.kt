package com.example.wanderlusty.feature_explore_tourism.domain.entity


data class CityDetailEntity(
    val id: String,
    val name: String,
    val image: String,
    val subtitle: String,
    val description: String,
    val map: String,
    val recommendation: List<TourismSpot>,
    val hiddenGems: HiddenGems,
    val hotels: List<Hotel>,
    val thingsToDo: ThingsToDo,
    val restaurants: List<Restaurant>,
    val rentals: List<Rental>
)

data class CityDetailOverview(
    val id: String,
    val name: String,
    val image: String,
    val subtitle: String,
    val description: String,
    val recommendation: List<TourismSpot>,
)

data class CityDetailHiddenGems(
    val id: String,
    val hiddenGems: HiddenGems,
)

data class CityDetailThingsToDo(
    val id: String,
    val thingsToDo: ThingsToDo,
)

data class CityDetailHotels(
    val id: String,
    val thingsToDo: ThingsToDo,
)

data class CityDetailRestaurants(
    val id: String,
    val restaurants: List<Restaurant>
)

data class CityDetailRentals(
    val id: String,
    val rentals: List<Rental>
)

data class TourismSpot(
    val id: String?,
    val name: String,
    val description: String,
    val image: List<String>,
    val rating: Number,
    val review: Number,
    val type: String,
    val location: String,
    val tourOption: List<TourOption>
)

data class HiddenGems(
    val hiddenTourism: List<TourismSpot>,
    val hiddenRestaurant: List<Restaurant>
)

data class Hotel(
    val name: String,
    val description: String,
    val phone: String,
    val email: String,
    val tag: List<String>,
    val languages: List<String>,
    val amenities: List<String>,
    val rating: Double,
    val review: Int,
    val price1: Int,
    val price2: Int,
    val site: String,
    val sponsored: Boolean,
    val image: List<String>
)

data class ThingsToDo(
    val tourism: List<TourismSpot>,
    val restaurant: List<Restaurant>
)

data class Restaurant(
    val name: String,
    val description: String,
    val image: List<String>,
    val rating: Double,
    val review: Double,
    val price1: Int,
    val price2: Int,
    val sponsored: Boolean,
    val type: String,
    val duration: String,
    val address: String,
    val tourOption: List<TourOption>
)

data class Rental(
    val name: String,
    val description: String,
    val phone: String,
    val email: String,
    val listVehicle: List<String>,
    val rating: Double,
    val review: Int,
    val price1: Int,
    val price2: Int,
    val site: String,
    val sponsored: Boolean,
    val image: List<String>
)