package com.example.wanderlusty.feature_explore_tourism.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TourismEntity(
    val id: String,
    val image: String,
    val title: String,
    val rating: Number,
    val review: Number,
    val type: String,
    val location: String,
    val price: String? = null,
) : Parcelable
