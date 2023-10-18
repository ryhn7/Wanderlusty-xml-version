package com.example.wanderlusty.feature_explore_tourism.presentation.detail_city

import com.example.wanderlusty.feature_explore_tourism.domain.entity.CityDetailEntity
import com.example.wanderlusty.feature_explore_tourism.domain.entity.CityDetailOverview
import com.example.wanderlusty.feature_explore_tourism.domain.entity.Hotel
import com.example.wanderlusty.feature_explore_tourism.domain.entity.Rental
import com.example.wanderlusty.feature_explore_tourism.domain.entity.Restaurant
import com.example.wanderlusty.feature_explore_tourism.domain.entity.TourismSpot

data class DetailCityState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val city: CityDetailEntity? = null,
    val overviewCity: CityDetailOverview? = null,
    val cardTourism: List<TourismSpot>? = null,
    val cardRestaurant : List<Restaurant>? = null,
    val hotels: List<Hotel>? = null,
    val rentVehicle : List<Rental>? = null,
)