package com.example.wanderlusty.feature_explore_tourism.presentation.detail_tourism

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.wanderlusty.feature_explore_tourism.data.datasource.TourismDataSource.getAllTourismCategories
import com.example.wanderlusty.feature_explore_tourism.domain.use_case.UseCasesExploreTourism
import com.example.wanderlusty.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailTourismViewModel @Inject constructor(
    private val useCases: UseCasesExploreTourism
) : ViewModel() {

    private val _detailState = MutableStateFlow(DetailTourismState())
    val detailState = _detailState.asStateFlow()


    fun getDetailTourism(id: String) {
        viewModelScope.launch {
            useCases.getTourismDetail(id).asFlow().collect() {
                when(it) {
                    is ResultState.Loading -> _detailState.value = _detailState.value.copy(
                        isLoading = true,
                        error = null,
                        tourism = null,
                        tourOption = null
                    )
                    is ResultState.Success -> _detailState.value = _detailState.value.copy(
                        isLoading = false,
                        error = null,
                        tourism = it.data,
                        tourOption = it.data.tourOption
                    )
                    is ResultState.Error -> _detailState.value = _detailState.value.copy(
                        isLoading = false,
                        error = it.error,
                        tourism = null,
                        tourOption = null
                    )
                }
            }
        }
    }
}
