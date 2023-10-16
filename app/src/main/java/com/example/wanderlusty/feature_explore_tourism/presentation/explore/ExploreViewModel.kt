package com.example.wanderlusty.feature_explore_tourism.presentation.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.wanderlusty.feature_explore_tourism.domain.use_case.UseCasesExploreTourism
import com.example.wanderlusty.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val useCases: UseCasesExploreTourism
) : ViewModel() {

    private val _exploreState = MutableStateFlow(ExploreState())
    val exploreState = _exploreState.asStateFlow()

    init {
        getAllTourismCategories()
    }

    fun getAllTourismCategories() {
        viewModelScope.launch {
            useCases.getAllTourismCategories().asFlow().collect() {
                when (it) {
                    is ResultState.Loading -> {
                        _exploreState.value = _exploreState.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }

                    is ResultState.Success -> {
                        _exploreState.value = _exploreState.value.copy(
                            isLoading = false,
                            error = null,
                            tourismCategories = it.data
                        )

                        getHiddenGems()
                    }

                    is ResultState.Error -> {
                        _exploreState.value = _exploreState.value.copy(
                            isLoading = false,
                            error = it.error ?: "An error occurred"
                        )
                    }
                }
            }
        }
    }

    fun getHiddenGems() {
        viewModelScope.launch {
            useCases.getHiddenGems().asFlow().collect() {
                when (it) {
                    is ResultState.Loading -> {
                        _exploreState.value = _exploreState.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }

                    is ResultState.Success -> {
                        _exploreState.value = _exploreState.value.copy(
                            isLoading = false,
                            error = null,
                            hiddenGems = it.data
                        )

                        getAllFavoritePlace()
                    }

                    is ResultState.Error -> {
                        _exploreState.value = _exploreState.value.copy(
                            isLoading = false,
                            error = it.error ?: "An error occurred"
                        )
                    }
                }
            }
        }
    }

    fun getAllFavoritePlace() {
        viewModelScope.launch {
            useCases.getAllFavoritePlace().asFlow().collect() {
                when (it) {
                    is ResultState.Loading -> {
                        _exploreState.value = _exploreState.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }

                    is ResultState.Success -> {
                        _exploreState.value = _exploreState.value.copy(
                            isLoading = false,
                            error = null,
                            favoritePlace = it.data
                        )

                        getAllSectionCitiesOne()
                    }

                    is ResultState.Error -> {
                        _exploreState.value = _exploreState.value.copy(
                            isLoading = false,
                            error = it.error ?: "An error occurred"
                        )
                    }
                }
            }
        }
    }

    fun getAllSectionCitiesOne() {
        viewModelScope.launch {
            useCases.getAllSectionCitiesOne().asFlow().collect() {
                when (it) {
                    is ResultState.Loading -> {
                        _exploreState.value = _exploreState.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }

                    is ResultState.Success -> {
                        _exploreState.value = _exploreState.value.copy(
                            isLoading = false,
                            error = null,
                            sectionCitiesOne = it.data
                        )
                    }

                    is ResultState.Error -> {
                        _exploreState.value = _exploreState.value.copy(
                            isLoading = false,
                            error = it.error ?: "An error occurred"
                        )
                    }
                }
            }
        }
    }
}