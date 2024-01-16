package com.arboleda.sportsapp.di

import androidx.lifecycle.MutableLiveData
import com.arboleda.sportsapp.data.endpoints.leagues.LeaguesApi
import com.arboleda.sportsapp.data.repositories.leagues.LeaguesRepositoryImpl
import com.arboleda.sportsapp.domain.repositories.leagues.LeaguesRepository
import com.arboleda.sportsapp.domain.usecases.leagues.LeaguesUC
import com.arboleda.sportsapp.presentation.states.LeagueState
import com.arboleda.sportsapp.presentation.viewmodels.countries.LeaguesViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object LeaguesModule {

    @Provides
    @ViewModelScoped
    fun provideLeaguesApi(retrofit: Retrofit): LeaguesApi {
        return retrofit.create(LeaguesApi::class.java)
    }

    @Provides
    @ViewModelScoped
    fun provideLeaguesRepository(
        leaguesApi: LeaguesApi,
    ): LeaguesRepository = LeaguesRepositoryImpl(leaguesApi = leaguesApi)

    @Provides
    @ViewModelScoped
    fun provideLeaguesUC(
        leaguesRepository: LeaguesRepository,
    ): LeaguesUC = LeaguesUC(leaguesRepository = leaguesRepository)

    @Provides
    @ViewModelScoped
    fun provideLeaguesViewModel(
        leaguesUC: LeaguesUC,
    ): LeaguesViewModel = LeaguesViewModel(
        leaguesUC = leaguesUC,
        _leagueState = MutableLiveData<LeagueState>(),
        _showDialog = MutableLiveData<Boolean>(),
    )
}
