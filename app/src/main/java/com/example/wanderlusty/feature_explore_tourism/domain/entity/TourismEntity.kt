package com.example.wanderlusty.feature_explore_tourism.domain.entity


data class TourismEntity(
    val id: String,
    val image: String,
    val title: String,
    val rating: Number,
    val review: Number,
    val type: String,
    val location: String,
    val price: String? = null,
    val description: String,
    val duration: String,
    val address: String,
    val tourOption: List<TourOption>? = null,
)

data class TourOption(
    val name: String,
    val rating: Number,
    val review: Number,
    val price: Number,
    val image: String,
)