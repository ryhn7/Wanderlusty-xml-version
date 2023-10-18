package com.example.wanderlusty.di

import com.example.wanderlusty.feature_explore_tourism.data.repository.TourismRepositoryImpl
import com.example.wanderlusty.feature_explore_tourism.domain.interface_repository.TourismRepository
import com.example.wanderlusty.feature_explore_tourism.domain.use_case.GetAllFavoritePlace
import com.example.wanderlusty.feature_explore_tourism.domain.use_case.GetAllSectionCitiesOne
import com.example.wanderlusty.feature_explore_tourism.domain.use_case.GetAllTourismCategories
import com.example.wanderlusty.feature_explore_tourism.domain.use_case.GetHiddenGems
import com.example.wanderlusty.feature_explore_tourism.domain.use_case.GetTourismDetail
import com.example.wanderlusty.feature_explore_tourism.domain.use_case.UseCasesExploreTourism
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TourismModule {

    @Provides
    @Singleton
    fun provideTourismRepository(): TourismRepository {
        return TourismRepositoryImpl.getInstance()
    }

    @Provides
    @Singleton
    fun provideExploreTourismUseCases(repository: TourismRepository): UseCasesExploreTourism {
        return UseCasesExploreTourism(
            getAllFavoritePlace = GetAllFavoritePlace(repository),
            getHiddenGems = GetHiddenGems(repository),
            getAllTourismCategories = GetAllTourismCategories(repository),
            getAllSectionCitiesOne = GetAllSectionCitiesOne(repository),
            getTourismDetail = GetTourismDetail(repository)
        )
    }
}