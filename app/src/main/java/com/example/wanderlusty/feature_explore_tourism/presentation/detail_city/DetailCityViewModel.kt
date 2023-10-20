package com.example.wanderlusty.feature_explore_tourism.presentation.detail_city

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.wanderlusty.feature_explore_tourism.domain.use_case.UseCasesExploreTourism
import com.example.wanderlusty.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailCityViewModel @Inject constructor(
    private val useCases: UseCasesExploreTourism
) : ViewModel() {


    private val _detailCityState = MutableStateFlow(DetailCityState())
    val detailCityState = _detailCityState.asStateFlow()

    fun getCityDetailOverview(id: String) {
        viewModelScope.launch {
            useCases.getCityDetailOverview(id).asFlow().collect() {
                when (it) {
                    is ResultState.Loading -> _detailCityState.value = _detailCityState.value.copy(
                        isLoading = true,
                        error = null,
                        overviewCity = null,
                        cardTourism = null
                    )

                    is ResultState.Success -> _detailCityState.value = _detailCityState.value.copy(
                        isLoading = false,
                        error = null,
                        overviewCity = it.data,
                        cardTourism = it.data.recommendation
                    )

                    is ResultState.Error -> _detailCityState.value = _detailCityState.value.copy(
                        isLoading = false,
                        error = it.error,
                        overviewCity = null,
                        cardTourism = null
                    )
                }
            }
        }
    }

    fun getCityDetailHiddenGems(id: String) {
        viewModelScope.launch {
            useCases.getCityDetailHiddenGems(id).asFlow().collect() {
                when (it) {
                    is ResultState.Loading -> _detailCityState.value = _detailCityState.value.copy(
                        isLoading = true,
                        error = null,
                        cardTourism = null,
                        cardRestaurant = null,
                    )

                    is ResultState.Success -> _detailCityState.value = _detailCityState.value.copy(
                        isLoading = false,
                        error = null,
                        cardTourism = it.data.hiddenGems.hiddenTourism,
                        cardRestaurant = it.data.hiddenGems.hiddenRestaurant,
                    )

                    is ResultState.Error -> _detailCityState.value = _detailCityState.value.copy(
                        isLoading = false,
                        error = it.error,
                        cardTourism = null,
                        cardRestaurant = null,
                    )
                }
            }
        }
    }
}
